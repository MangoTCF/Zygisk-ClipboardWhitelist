# Zygisk - Clipboard Whitelist

A [Zygisk](https://github.com/topjohnwu/zygisk-module-sample) module that lets selected apps read the clipboard in the background on Android 10+ (e.g. KDE Connect clipboard sync).

This is a fork of [Kr328/Riru-ClipboardWhitelist](https://github.com/Kr328/Riru-ClipboardWhitelist), which is no longer maintained, updated to work on Android 16 with KernelSU.

> [!NOTE]
> **AI disclosure:** the changes in this fork (Android 14–16 support, KernelSU fixes, build changes) were written by an AI coding assistant (Claude, by Anthropic), directed and tested on a real device by me. The original module is Kr328's work. Review the code yourself if that matters to you.

## Requirements

* Android 10+ (tested on Android 16)
* A Zygisk implementation:
  - Magisk with Zygisk enabled, or
  - KernelSU / APatch with a Zygisk module (tested with KernelSU + [NeoZygisk](https://github.com/JingMatrix/NeoZygisk))

## Usage

1. Install the zip from [Releases](../../releases) in your root manager and reboot.
2. After boot (and after unlocking), the **Clipboard Whitelist** app is installed. Open it and tick the apps that should get background clipboard access.
3. Ticked apps are force-stopped once so the change takes effect.

## How it works

The module hooks `system_server` and wraps the clipboard service. When a whitelisted app calls a clipboard read method, the call is made on behalf of the current default input method, which Android always allows to access the clipboard.

## Changes from upstream

* **Android 13–16 support:** the clipboard service methods gained `attributionTag` (13) and `deviceId` (14+) parameters, so the old hooks never matched. All three signatures are now handled.
* **No native binder hook:** the proxy is registered directly with ServiceManager instead of patching ART's JNI table in `system_server`, which could crash it and stop the phone from finishing boot.
* **KernelSU support:** the manager app is installed as a regular app on boot instead of being mounted into `/system/app`. KernelSU hides module mounts from apps, so the mounted app crashed on launch.
* **Installer fixes:** `minSdkVersion` was written as `null`, and the data directory's SELinux context was never applied.
* **Self-contained build:** ZygoteLoader and MagicLibrary (MIT, by Kr328) are vendored under `external/` because their Maven repository is offline.
* The Riru variant was dropped.

## Multi-User Support

Add the "Clipboard Whitelist" app to other users and configure it there.

## Build

1. Install JDK 17 and the Android SDK, with NDK `26.1.10909125` and CMake `3.22.1`.

2. Point Gradle at the SDK. The vendored builds need it too, so use the environment variable:

   ```bash
   export ANDROID_HOME=/path/to/android/sdk
   ```

3. Configure the manager app signing in `signing.properties`:

   ```properties
   keystore.path=/path/to/your/keystore.jks
   keystore.password=<keystore password>
   key.alias=<key alias>
   key.password=<key password>
   ```

4. Build:

   ```bash
   ./gradlew module:assembleRelease
   ```

5. The zip is at `module/build/outputs/magisk/zygisk/release/zygisk-clipboard-whitelist-<version>.zip`.

## License

GPL-3.0, same as upstream. Vendored libraries in `external/` are MIT licensed (see their `LICENSE` files).
