package iDict;

import java.io.IOException;  

import com.jacob.activeX.ActiveXComponent;  
import com.jacob.com.Dispatch;  
import com.jacob.com.Variant;  
  
public class Speak {  
  
    public void speak(String str) throws IOException {  
         
        ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");  
        Dispatch sapo = sap.getObject();  
        
        try {  
  
            // ���� 0-100  
            sap.setProperty("Volume", new Variant(100));  
            // �����ʶ��ٶ� -10 �� +10  
            sap.setProperty("Rate", new Variant(1));  
            // ִ���ʶ�  
            Dispatch.call(sapo, "Speak", new Variant(str));  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            sapo.safeRelease();  
            sap.safeRelease();  
        }  
        }  
}  