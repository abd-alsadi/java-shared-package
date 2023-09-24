package com.core.shared.Abstructions.StorageManager;

public interface IStorageManager {
    public String createFile(String path,byte[] data);
    public String updateFile(String path,byte[] data);
    public boolean isExistsFile(String path);
    public byte[] getFile(String path);
    public boolean deleteFile(String path);
}
