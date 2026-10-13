package android.content;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

public interface IClipboard extends IInterface {
    // Android 10 - 12
    ClipData getPrimaryClip(String pkg, int userId) throws RemoteException;

    ClipDescription getPrimaryClipDescription(String callingPackage, int userId) throws RemoteException;

    boolean hasPrimaryClip(String callingPackage, int userId) throws RemoteException;

    boolean hasClipboardText(String callingPackage, int userId) throws RemoteException;

    void addPrimaryClipChangedListener(IOnPrimaryClipChangedListener listener,
                                       String callingPackage, int userId) throws RemoteException;

    void removePrimaryClipChangedListener(IOnPrimaryClipChangedListener listener,
                                          String callingPackage, int userId) throws RemoteException;

    // Android 13
    ClipData getPrimaryClip(String pkg, String attributionTag, int userId) throws RemoteException;

    ClipDescription getPrimaryClipDescription(String callingPackage, String attributionTag, int userId) throws RemoteException;

    boolean hasPrimaryClip(String callingPackage, String attributionTag, int userId) throws RemoteException;

    boolean hasClipboardText(String callingPackage, String attributionTag, int userId) throws RemoteException;

    void addPrimaryClipChangedListener(IOnPrimaryClipChangedListener listener,
                                       String callingPackage, String attributionTag, int userId) throws RemoteException;

    void removePrimaryClipChangedListener(IOnPrimaryClipChangedListener listener,
                                          String callingPackage, String attributionTag, int userId) throws RemoteException;

    // Android 14+
    ClipData getPrimaryClip(String pkg, String attributionTag, int userId, int deviceId) throws RemoteException;

    ClipDescription getPrimaryClipDescription(String callingPackage, String attributionTag, int userId, int deviceId) throws RemoteException;

    boolean hasPrimaryClip(String callingPackage, String attributionTag, int userId, int deviceId) throws RemoteException;

    boolean hasClipboardText(String callingPackage, String attributionTag, int userId, int deviceId) throws RemoteException;

    void addPrimaryClipChangedListener(IOnPrimaryClipChangedListener listener,
                                       String callingPackage, String attributionTag, int userId, int deviceId) throws RemoteException;

    void removePrimaryClipChangedListener(IOnPrimaryClipChangedListener listener,
                                          String callingPackage, String attributionTag, int userId, int deviceId) throws RemoteException;

    abstract class Stub extends Binder implements IClipboard {
        public static IClipboard asInterface(IBinder binder) {
            throw new IllegalArgumentException("Stub!");
        }

        @Override
        public IBinder asBinder() {
            throw new IllegalArgumentException("Stub");
        }
    }
}