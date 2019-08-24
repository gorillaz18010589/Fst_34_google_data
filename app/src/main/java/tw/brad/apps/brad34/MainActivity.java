package tw.brad.apps.brad34;
//玩Firebase
//https://console.firebase.google.com/project/brad34-5ad77/database/brad34-5ad77/data
//1.先布局玩遠端資料
//tools => fire=> Realtime date =>
//1.連線要先登入（創建專案）上傳灌過去等綠燈亮
//2.新增遠端資料　appect change允許他改一些code 等綠燈亮
//3.處態Firebase的詳細規則:你要不要阻太一下,因為現在規則很嚴,把全線打開點configuer your ,直接到主控台
//*主控台 => 上傳允許 =>Database =>建立資料庫(先用策式)的 =>Realtime Database點規則=>
//4.資料寫入寫到他遠端複製範例
//5.資料讀取ｄａｔａ//read writer設定為true再發布,再創一個會員類別
//6.
//7.
//8

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private  FirebaseDatabase database;
    private TextView mesg;
    private Switch aSwitch;
    private  boolean isChecked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mesg = findViewById(R.id.mesg);//取得訊息物件實體
        aSwitch = findViewById(R.id.switchValue); //取得開關物件實體

        database = FirebaseDatabase.getInstance();
        readData();//呼叫一次性的readData
    }
        //1.這段式複製第四個Write to your data (把這邊寫的資料,寫到遠端)
    private  void writeData(){
        // Write a message to the database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();已經寫道創造區
        DatabaseReference myRef = database.getReference("message");//key
        myRef.setValue("Hello, World!");//設定value(值)

        //按按鈕可以產生結點
        //Member會員少一段


        //再創一個節點,把我資料結構上傳
        DatabaseReference myRef2 = database.getReference("data");
        LinkedList<LinkedList<HashMap<String,String>>> data = new LinkedList<>();
        LinkedList<HashMap<String,String>> data1 = new LinkedList<>();
        LinkedList<HashMap<String,String>> data2 = new LinkedList<>();
        data.add(data1); data.add(data2);


    }

    //讀取遠端資料
    private  void readData(){
        // Read from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();//這邊要另外加上這個宣告讓他看到
        DatabaseReference myRef = database.getReference("message");//key
        myRef.addValueEventListener(new ValueEventListener() {
            @Override //當資料異動時
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);//取得data.的value(從類別反射回來)
                Log.v("brad","Value is:" + value); //異動時顯示更改內容
                mesg.setText(value);


                //傳回類別的屬性少一段

                //有改內容時開關切換
                isChecked = !isChecked; //開關true false
                aSwitch.setChecked(isChecked); //開

            }

            @Override//當有錯誤時
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.v("brad","Error is" + error);
            }
        });
    }

    public void test1(View view){
        writeData();//按下去回連到goggle
    }
}
