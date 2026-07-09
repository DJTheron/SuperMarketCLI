#!/usr/bin/env bash
# Installs the SuperMarketCLI binary from the latest GitHub release.
# Usage: curl -fsSL https://raw.githubusercontent.com/DJTheron/SuperMarketCLI/main/install.sh | sh

set -euo pipefail

REPO="DJTheron/SuperMarketCLI"
INSTALL_DIR="/usr/local/lib/supermarketcli"
BIN_LINK="/usr/local/bin/supermarketcli"

os="$(uname -s)"
case "$os" in
  Darwin) asset_name="SuperMarketCLI-macos.zip" ;;
  Linux)  asset_name="SuperMarketCLI-linux.zip" ;;
  *)
    echo "error: unsupported OS '$os'. Download a Windows .msi manually from:" >&2
    echo "  https://github.com/${REPO}/releases/latest" >&2
    exit 1
    ;;
esac

for cmd in curl unzip; do
  if ! command -v "$cmd" >/dev/null 2>&1; then
    echo "error: '$cmd' is required but not installed." >&2
    exit 1
  fi
done

echo "Looking up latest release..."
download_url="$(
  curl -fsSL "https://api.github.com/repos/${REPO}/releases/latest" \
    | grep '"browser_download_url"' \
    | grep "$asset_name" \
    | sed -E 's/.*"browser_download_url": *"([^"]+)".*/\1/'
)"

if [ -z "$download_url" ]; then
  echo "error: could not find a '$asset_name' asset on the latest release." >&2
  exit 1
fi

tmp_dir="$(mktemp -d)"
trap 'rm -rf "$tmp_dir"' EXIT

echo "Downloading $asset_name..."
curl -fsSL "$download_url" -o "$tmp_dir/$asset_name"

echo "Unpacking..."
unzip -q "$tmp_dir/$asset_name" -d "$tmp_dir/extracted"

if [ "$os" = "Darwin" ]; then
  binary_path="Contents/MacOS/SuperMarketCLI"
  app_root="$tmp_dir/extracted/SuperMarketCLI.app"
else
  binary_path="bin/SuperMarketCLI"
  app_root="$tmp_dir/extracted/SuperMarketCLI"
fi

echo "Installing to $INSTALL_DIR (requires sudo)..."
sudo rm -rf "$INSTALL_DIR"
sudo mkdir -p "$(dirname "$INSTALL_DIR")"
sudo mv "$app_root" "$INSTALL_DIR"
sudo ln -sf "$INSTALL_DIR/$binary_path" "$BIN_LINK"

echo ""
echo "Installed! Try:"
echo "  supermarketcli -h"
