package org.unhcr.sid.helpers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

public class ZXingHelper {
	
	public static byte[] getQRCodeImage(String text, int width, int height) {
		try {
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);
			return byteArrayOutputStream.toByteArray();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String decodeQRCode(File qrCodeimage) throws IOException {
		System.out.println("qrCodeimage "+qrCodeimage);
		Result result = null;
		//for (File file : qrCodeimage.listFiles()) {
			System.out.println("file "+qrCodeimage);
			 BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
		        //System.out.println("bufferedImage "+bufferedImage);
		        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
		       // System.out.println("source "+source);
		        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		       // System.out.println("bitmap "+bitmap);

		            
					try {
						result = new MultiFormatReader().decode(bitmap);
						System.out.println("result "+result);
					} catch (NotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            
		        
	//	}
		return result.getText();
       
    }
	
	public static String ReadQrcode(File file) {
		String resu="";
		System.out.println("nom du fichier "+file.getName());
		System.out.println("chemin du fichier "+file.getPath());
		try {
			BufferedImage buffer = ImageIO.read(file);
			LuminanceSource luminanceSource = new BufferedImageLuminanceSource(buffer);
			BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
			Result result = new MultiFormatReader().decode(binaryBitmap);
			resu = result.getText();
			System.out.println("result "+resu);
		} catch (IOException | NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return resu;
	}

}
