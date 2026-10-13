MODDIR=${0%/*}
FLAG_FILE="$MODDIR/.module_updated"
PACKAGE="com.github.kr328.clipboard"

while [ "$(getprop "sys.boot_completed")" != "1" ] ; do
  sleep 5
done

# The manager app is installed as a regular app rather than mounted into /system/app:
# KernelSU unmounts module overlays from non-root app processes, so a mounted copy
# cannot read its own APK and crashes on launch.
if [ -f "$FLAG_FILE" ] || ! pm path "$PACKAGE" > /dev/null 2>&1 ; then
  TMP_APK="/data/local/tmp/clipboard-whitelist-manager.apk"

  cp -f "$MODDIR/manager.apk" "$TMP_APK"
  chmod 0644 "$TMP_APK"

  if ! pm install -r "$TMP_APK" ; then
    # Signature changed (e.g. upgrading from the old system app), reinstall.
    pm uninstall "$PACKAGE"
    pm install "$TMP_APK"
  fi

  rm -f "$TMP_APK"
fi

if [ ! -f "$FLAG_FILE" ]; then
  exit 0
fi

while [ ! -d "/sdcard/Android" ] ; do
  sleep 5
done

/system/bin/am start -n com.github.kr328.clipboard/.InstalledActivity

rm -rf "$FLAG_FILE"
