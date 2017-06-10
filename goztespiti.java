package openCvGozTespit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;


import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;
import org.opencv.videoio.VideoCapture;



public class openCvGozTespiti {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat circles=new Mat();;
		Mat src= new Mat(); 
		Mat gray = new Mat();
		src = Imgcodecs.imread("C:\\Users\\ASUS PC\\Desktop\\Bitirme Dosyalar\\BitirmeDataSet\\emre.jpg");
		
		String cascade_file1 = "C:\\opencv\\sources\\data\\haarcascades\\haarcascade_lefteye_2splits.xml";
		String cascade_file2 = "C:\\opencv\\sources\\data\\haarcascades\\haarcascade_righteye_2splits.xml";
		String cascade_file3 = "C:\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_default.xml";
	   
		CascadeClassifier cascadeEyeClassifier1 = new CascadeClassifier();
		CascadeClassifier cascadeEyeClassifier2= new CascadeClassifier();
		CascadeClassifier cascadeEyeClassifier3= new CascadeClassifier();
		
		if (cascade_file1.isEmpty() || !cascadeEyeClassifier1.load(cascade_file1))
		{
			System.out.println("Hata: cascade dosyası bulunamadı!n"); 
			//return -1;
		}
		if (cascade_file2.isEmpty() || !cascadeEyeClassifier2.load(cascade_file2))
		{
			System.out.println("Hata: cascade dosyası bulunamadı!n");
			
			//return -1;
		}
		if (cascade_file3.isEmpty() || !cascadeEyeClassifier3.load(cascade_file3))
 		{
 			System.out.println("Hata: cascade dosyası bulunamadı!"); 
 			//return -1;
 		}
		
		
			Imgproc.cvtColor(src, gray,Imgproc.COLOR_RGB2GRAY); // görüntüyü gray levele çevirip grayın içerisine atar
			
			Imgproc.equalizeHist(gray, gray); //Histogram eşitleme yapar.
			
			MatOfRect faces = new MatOfRect();
			
			cascadeEyeClassifier1.detectMultiScale(gray, faces, 1.1, 2, 0 | Objdetect.CASCADE_SCALE_IMAGE,
					new Size(30, 30), new Size(50,50));
			
			
			// bulunan yüz ifadelerini dikdörtgen içine alma


			Rect[] facesArray = faces.toArray();
			Rect rect = new Rect();
			Rect rect2 =new Rect();
			for (int i = 0; i < facesArray.length; i++)
			{
				 rect = facesArray[i];
				
				
				
				MatOfRect faces2 = new MatOfRect();
				cascadeEyeClassifier2.detectMultiScale(gray, faces2, 1.1, 2, 0 | Objdetect.CASCADE_SCALE_IMAGE,
						new Size(30,30), new Size(50,50));
				
				
				Rect[] facesArray2 = faces2.toArray();
				for (int j = 0; j < facesArray2.length ; j++) {
					
					rect2 = facesArray2[j];
				
				
				}
		
		}
			
			  MatOfRect faceDetections = new MatOfRect();
		         
		       cascadeEyeClassifier3.detectMultiScale(src, faceDetections);
		       Rect rect3 = new Rect();
		       Rect[] facesArray3= faceDetections.toArray(); 
		       for (int i=0 ;i< facesArray3.length;i++) {
		    	   rect3 = facesArray3[i];
		            Imgproc.rectangle(src, new Point(rect3.x, rect3.y), new Point(rect3.x + rect3.width, rect3.y + rect3.height),
		                     new Scalar(0, 255, 0));
		         }
			
			//Sag Göz , Sol Goz ve yüzün bulundugu dikdortgenleri ayırıyoruz
		       
			Rect dikdortgen=new Rect(new Point(rect.x, rect.y),new Point(rect.x + rect.width, rect.y + rect.height));
			Rect dikdortgen2=new Rect(new Point(rect2.x, rect2.y),new Point(rect2.x + rect2.width, rect2.y + rect2.height));
			Rect dikdortgen3 =new Rect(new Point(rect3.x, rect3.y), new Point(rect3.x + rect3.width, rect3.y + rect3.height));
			
			//Yeni bir mat nesnesi oluşturuyoruz ve okunan görüntüye dikdörtgen ebatlarında kırpma işlemi uyguluyoruz
			
			Mat yeniGoruntu=new Mat(src,dikdortgen);
			Mat yeniGoruntu2=new Mat(src,dikdortgen2);
			Mat yeniGoruntu3 =new Mat(src,dikdortgen3);
			
			// Bulunan Sol Gozde goz bebeginin olup olmadıgı bulunarak onun tespiti yapılıyor
			
			if(yeniGoruntu.rows()!=0){
			
			Mat gray2 = new Mat(yeniGoruntu.rows(), yeniGoruntu.cols(), CvType.CV_8SC1);
			Imgproc.cvtColor(yeniGoruntu, gray2, Imgproc.COLOR_RGB2GRAY); 		
			
			Imgproc.Canny(gray2, gray2, 80, 100);										
			
			
			//Bulunan gozdeki gozbebegi tespit ediliyor.
			Imgproc.HoughCircles(gray2, circles, Imgproc.CV_HOUGH_GRADIENT, 2, 10, 160, 50, 10, 20); 
			Point pt = new Point();
			// Tespit edilen goz bebegi yuvarlak icine aliniyor. 
			for (int i = 0; i < circles.cols(); i++){
				double data[] = circles.get(0, i);
				pt.x = data[0];
				pt.y = data[1];
				double rho = data[2];
				Imgproc.circle(yeniGoruntu, pt, (int)rho, new Scalar(0, 200, 0), 5);
				
			}
			}
			
			Imgcodecs.imwrite("SolGoz.jpg", yeniGoruntu);
			Imgcodecs.imwrite("SagGoz.jpg", yeniGoruntu2);
			Imgcodecs.imwrite("Yuz.jpg", yeniGoruntu3);
			Imgcodecs.imwrite("AnaResim.jpg", src);
			
			
			
			int k=0;
			// Goz ve yuzun bulunup bulunmadigini bulabilmek için size degerleri aliniyor. 
			
			Size sizeA = yeniGoruntu.size();
			Size sizeB = yeniGoruntu3.size();
			
			
			

			// Bulunan Gözdeki toplan pixel sayisi ve satir,sutunda bulunan pixeller hesaplaniyor
			for (int i = 0; i < sizeA.height; i++){
			    for (int j = 0; j < sizeA.width; j++) {
			        double[] data = yeniGoruntu.get(i, j);
			        k=k+1;
			        data[0] = data[0] ;
			        data[1] = data[1];
			        data[2] = data[2];
			       
			        }
			}
			System.out.println("pixel sayisisi= "+k);
			System.out.println("toplam x= "+sizeA.height+" toplam y= "+sizeA.width);
			
			
			File dosya = new File("C:\\Users\\ASUS PC\\Desktop\\Bitirme Dosyalar\\ExceleYaz.txt");
	          FileWriter yazici = new FileWriter(dosya,true);
	          BufferedWriter yaz = new BufferedWriter(yazici); 
	          
	          
	          
			if(sizeB.height!=0){
				System.out.println("Yuz Bulundu.");
				yaz.write(" Yuz Bulundu");
				if(sizeA.height!=0){
					System.out.println("Goz Bulundu.");
					yaz.write(" Goz Bulundu");
					if(circles.cols()!=0){
						System.out.println("Goz Acik.");
						yaz.write(" Goz Acik");
					}else{
						System.out.println("Goz Kapali.");
						yaz.write(" Goz Kapali");
					}
					
				}else{
					System.out.println("Goz Bulunamadi.");
					yaz.write(" Goz Bulunamadi");
					
				}
				if(sizeA.height!=0){
					yaz.write(" Engel yok");
					System.out.println("Engel Yok.");
				}else{
					yaz.write(" Engel var");
					System.out.println("Engel Var.");
				}
				
				
				
				
			}else{
				System.out.println("Yuz Bulunamadi.");
				yaz.write(" Yuz Bulunamadi");
			}
			yaz.newLine();
			yaz.close();
			System.out.println("Çalışma tamamlandı");
   }

}
