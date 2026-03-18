param(
  [Parameter(Mandatory=$true)][string]$Ssid,
  [Parameter(Mandatory=$true)][string]$AdbTarget
)

function Get-ConnectedSsid {
  $out = netsh wlan show interfaces
  $line = $out | Select-String -Pattern '^\s*SSID\s*:\s*(.+)$' | Select-Object -First 1
  if (-not $line) { return "" }
  return ($line.Matches[0].Groups[1].Value).Trim()
}

function Wait-ForNetwork([int]$timeoutSeconds = 25) {
  $sw = [Diagnostics.Stopwatch]::StartNew()
  while ($sw.Elapsed.TotalSeconds -lt $timeoutSeconds) {
    $hasRoute = (Get-NetRoute -DestinationPrefix "0.0.0.0/0" -ErrorAction SilentlyContinue) -ne $null
    if ($hasRoute) { return $true }
    Start-Sleep -Milliseconds 400
  }
  return $false
}

$cur = Get-ConnectedSsid
if ($cur -ne $Ssid) {
  Write-Host "Current SSID: '$cur' -> connecting to '$Ssid'..."
  netsh wlan connect name="$Ssid" | Out-Null
  if (-not (Wait-ForNetwork)) { throw "Timed out waiting for network after connecting to '$Ssid'." }
} else {
  Write-Host "Already connected to '$Ssid'."
}

adb kill-server | Out-Null
adb start-server | Out-Null
adb connect $AdbTarget | Out-Host
adb devices -l | Out-Host