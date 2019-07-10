package com.benjamin.utils;

import android.content.Context;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.progress.ProgressMonitor;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

	/**
	 * 压缩文件
	 * @return 压缩成功或失败
	 */
	public static boolean zipFile(String inFilePath, String outFilePath){
		ZipOutputStream zipOutputStream;
		try {
				zipOutputStream = new ZipOutputStream(new FileOutputStream(outFilePath));
				File file = new File(inFilePath);
				//压缩  
				ZipFiles(file.getParent()+ File.separator, file.getName(), zipOutputStream);
		        //完成,关闭  
		        zipOutputStream.finish();  
		        zipOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * 解压文件
	 * @param unZipfileName
	 * @param mDestPath
	 */
	public static void unZipFile(String unZipfileName, String mDestPath) {
	       if (!mDestPath.endsWith("/")) {  
	           mDestPath = mDestPath + "/";  
	       }  
	       FileOutputStream fileOut = null;
	       ZipInputStream zipIn = null;
	       ZipEntry zipEntry = null;
	       File file = null;
	       int readedBytes = 0;  
	       byte buf[] = new byte[4096];  
	       try {  
	           zipIn = new ZipInputStream(new BufferedInputStream(new FileInputStream(unZipfileName)));
	           while ((zipEntry = zipIn.getNextEntry()) != null) {  
	               file = new File(mDestPath + zipEntry.getName());
	               if (zipEntry.isDirectory()) {  
	                   file.mkdirs();  
	               } else {  
	                   // 如果指定文件的目录不存在,则创建之.  
	                   File parent = file.getParentFile();
	                   if (!parent.exists()) {  
	                       parent.mkdirs();  
	                   }  
	                   fileOut = new FileOutputStream(file);
	                   while ((readedBytes = zipIn.read(buf)) > 0) {  
	                       fileOut.write(buf, 0, readedBytes);  
	                   }  
	                   fileOut.close();  
	               }  
	               zipIn.closeEntry();  
	           }  
	       } catch (IOException ioe) {
	           ioe.printStackTrace();  
	       }  
	   } 
	
	/**
     * 压缩文件 
     * @param folderString 
     * @param fileString 
     * @param zipOutputSteam 
     */
    private static void ZipFiles(String folderString, String fileString, ZipOutputStream zipOutputSteam)throws Exception {
        android.util.Log.v("XZip", "ZipFiles(String, String, ZipOutputStream)");  
          
        if(zipOutputSteam == null)  
            return;  
          
        File file = new File(folderString+fileString);
          
        //判断是不是文件  
        if (file.isFile()) {  
  
            ZipEntry zipEntry =  new ZipEntry(fileString);
            FileInputStream inputStream = new FileInputStream(file);
            zipOutputSteam.putNextEntry(zipEntry);  
              
            int len;  
            byte[] buffer = new byte[4096];  
              
            while((len=inputStream.read(buffer)) != -1)  
            {  
                zipOutputSteam.write(buffer, 0, len);  
            }  
              
            zipOutputSteam.closeEntry();  
        }  
        else {  
              
            //文件夹的方式,获取文件夹下的子文件  
            String fileList[] = file.list();
              
            //如果没有子文件, 则添加进去即可  
            if (fileList.length <= 0) {  
                ZipEntry zipEntry =  new ZipEntry(fileString+ File.separator);
                zipOutputSteam.putNextEntry(zipEntry);  
                zipOutputSteam.closeEntry();                  
            }  
              
            //如果有子文件, 遍历子文件  
            for (int i = 0; i < fileList.length; i++) {  
                ZipFiles(folderString, fileString+ File.separator+fileList[i], zipOutputSteam);
            }//end of for  
      
        }//end of if  
          
    }//end of func

	public static void unZip(Context context, String assetFileName, String outputDirectory, boolean isReWrite) throws IOException {
		File file = new File(outputDirectory);
		if (!file.exists()) {
			file.mkdirs();
		}

		InputStream inputStream = context.getAssets().open(assetFileName);
		ZipInputStream zipInputStream = new ZipInputStream(inputStream);
		ZipEntry zipEntry = zipInputStream.getNextEntry();
		byte[] buffer = new byte[1048576];

		for(boolean var9 = false; zipEntry != null; zipEntry = zipInputStream.getNextEntry()) {
			if (zipEntry.isDirectory()) {
				file = new File(outputDirectory + File.separator + zipEntry.getName());
				if (isReWrite || !file.exists()) {
					file.mkdir();
				}
			} else {
				file = new File(outputDirectory + File.separator + zipEntry.getName());
				if (isReWrite || !file.exists()) {
					file.createNewFile();
					FileOutputStream fileOutputStream = new FileOutputStream(file);

					int count;
					while((count = zipInputStream.read(buffer)) > 0) {
						fileOutputStream.write(buffer, 0, count);
					}

					fileOutputStream.close();
				}
			}
		}

		zipInputStream.close();
	}

	public static void unZip(File zipFile, String outputFilePath, OnUnZipCallback onUnZipCallback, boolean isDeleteZip) throws ZipException {
		unZip(zipFile, (String)null, outputFilePath, onUnZipCallback, isDeleteZip);
	}

	public static void unZip(final File zipFile, String password, String outputFilePath, final OnUnZipCallback onUnZipCallback, final boolean isDeleteZip) throws ZipException {
		ZipFile zFile = new ZipFile(zipFile);
		zFile.setFileNameCharset("UTF-8");
		if (!zFile.isValidZipFile()) {
			throw new ZipException("该文件不是有效的压缩包!");
		} else {
			File destDir = new File(outputFilePath);
			if (destDir.isDirectory() && !destDir.exists()) {
				destDir.mkdir();
			}

			if (zFile.isEncrypted()) {
				zFile.setPassword(password == null ? "" : password);
			}

			final ProgressMonitor progressMonitor = zFile.getProgressMonitor();
			Thread thread = new Thread(new Runnable() {
				public void run() {
					try {
//						int precentDone = false;
						if (onUnZipCallback != null) {
							onUnZipCallback.onStart();

							int precentDonex;
							do {
								Thread.sleep(1L);
								precentDonex = progressMonitor.getPercentDone();
								onUnZipCallback.onUnZipping(precentDonex);
							} while(precentDonex < 100);

							onUnZipCallback.onSuccess();
							return;
						}
					} catch (InterruptedException var5) {
						onUnZipCallback.onFail();
						var5.printStackTrace();
						return;
					} finally {
						if (isDeleteZip) {
							zipFile.delete();
						}

					}

				}
			});
			thread.start();
			zFile.setRunInThread(true);
			zFile.extractAll(outputFilePath);
		}
	}

	public interface OnUnZipCallback {
		void onStart();

		void onUnZipping(int var1);

		void onFail();

		void onSuccess();
	}
}
