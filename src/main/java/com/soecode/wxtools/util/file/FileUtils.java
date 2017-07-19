package com.soecode.wxtools.util.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

	/**
	 * 创建临时文件
	 * 
	 * @param inputStream
	 * @param name
	 *            文件名
	 * @param ext
	 *            扩展名
	 * @param tmpDirFile
	 *            临时文件夹目录
	 * @return
	 * @throws IOException
	 */
	public static File createTmpFile(InputStream inputStream, String name, String ext, File tmpDirFile)
			throws IOException {
		FileOutputStream fos = null;
		try {
			File tmpFile;
			if (tmpDirFile == null) {
				tmpFile = File.createTempFile(name, '.' + ext);
			} else {
				tmpFile = File.createTempFile(name, '.' + ext, tmpDirFile);
			}
			tmpFile.deleteOnExit();//JVM结束时，删除该临时目录
			fos = new FileOutputStream(tmpFile);
			int read = 0;
			byte[] bytes = new byte[1024 * 100];
			while ((read = inputStream.read(bytes)) != -1) {
				fos.write(bytes, 0, read);
			}
			fos.flush();
			return tmpFile;
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	/**
	 * 创建【永久】文件
	 * 
	 * @param inputStream
	 * @param name
	 *            文件名
	 * @param ext
	 *            扩展名
	 * @param materialDirFile
	 *            永久文件夹目录
	 * @return
	 * @throws IOException
	 */
	public static File createMaterialFile(InputStream inputStream, String name, String ext, File materialDirFile)
			throws IOException {
		FileOutputStream fos = null;
		try {
			File materialFile;
			if (materialDirFile == null) {
				materialFile = File.createTempFile(name, '.' + ext);
			} else {
				materialFile = File.createTempFile(name, '.' + ext, materialDirFile);
			}
			fos = new FileOutputStream(materialFile);
			int read = 0;
			byte[] bytes = new byte[1024 * 100];
			while ((read = inputStream.read(bytes)) != -1) {
				fos.write(bytes, 0, read);
			}
			fos.flush();
			return materialFile;
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * 创建【永久】文件
	 * 
	 * @param inputStream
	 * @param name
	 *            文件名
	 * @param ext
	 *            扩展名
	 * @return
	 * @throws IOException
	 */
	public static File createMaterialFile(InputStream inputStream, String name, String ext) throws IOException {
		return createMaterialFile(inputStream, name, ext, null);
	}
	
	/**
	 * 创建【临时】文件
	 * 
	 * @param inputStream
	 * @param name
	 *            文件名
	 * @param ext
	 *            扩展名
	 * @return
	 * @throws IOException
	 */
	public static File createTmpFile(InputStream inputStream, String name, String ext) throws IOException {
		return createTmpFile(inputStream, name, ext, null);
	}

}
