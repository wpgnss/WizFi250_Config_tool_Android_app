package wiznet.wizfi250_config_tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2016-03-10.
 */
public class SetWizFi250Activity extends Fragment {

    Context mContext;

    SharedPreferences mPref;
    SharedPreferences.Editor editor;

    Button button_Save;
    EditText WizFi250_SSID;
    EditText WizFi250_PASS;

    EditText WizFi250_AIRCMD_IP;
    EditText WizFi250_AIRCMD_PORT;

    EditText AP_SSID;
    EditText AP_PASS;
    EditText SERVER_IP;
    EditText SERVER_PORT;
    CheckBox SSL_ENABLE;
    CheckBox DATAMODE_ENABLE;

    public SetWizFi250Activity(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.step1_setting_layout, null);

//        mPref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        WizFi250_SSID = (EditText) view.findViewById(R.id.txt_wizfi250_ssid);
        WizFi250_PASS = (EditText) view.findViewById(R.id.txt_wizfi250_pass);

        WizFi250_AIRCMD_IP = (EditText) view.findViewById(R.id.txt_aircmd_ip);
        WizFi250_AIRCMD_PORT = (EditText) view.findViewById(R.id.txt_aircmd_port);

        AP_SSID = (EditText) view.findViewById(R.id.txt_apname_ssid);
        AP_PASS = (EditText) view.findViewById(R.id.txt_apname_pass);

        SERVER_IP = (EditText) view.findViewById(R.id.txt_dest_ip);
        SERVER_PORT = (EditText) view.findViewById(R.id.txt_dest_port);
        SSL_ENABLE = (CheckBox) view.findViewById(R.id.checkbox_ssl_enable);
        DATAMODE_ENABLE = (CheckBox) view.findViewById(R.id.checkbox_datamode_enable);

        mPref = getActivity().getSharedPreferences("setting", 0);
        editor= mPref.edit();


        button_Save = (Button) view.findViewById(R.id.btn_config);

        button_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wizfi250_ssid = WizFi250_SSID.getText().toString();
                String wizfi250_pass = WizFi250_PASS.getText().toString();

                String wizfi250_aircmd_ip = WizFi250_AIRCMD_IP.getText().toString();
                String wizfi250_aircmd_port = WizFi250_AIRCMD_PORT.getText().toString();

                String ap_ssid = AP_SSID.getText().toString();
                String ap_pass = AP_PASS.getText().toString();

                String server_ip = SERVER_IP.getText().toString();
                String sever_port = SERVER_PORT.getText().toString();

                Boolean ssl_enable = SSL_ENABLE.isChecked();
                Boolean datamode_enable = DATAMODE_ENABLE.isChecked();

                editor.putString("wizfi250_ssid_pre", wizfi250_ssid);
                editor.putString("wizfi250_pass", wizfi250_pass);
                editor.putString("wizfi250_aircmd_ip", wizfi250_aircmd_ip);
                editor.putString("wizfi250_aircmd_port", wizfi250_aircmd_port);
                editor.putString("ap_ssid", ap_ssid);
                editor.putString("ap_pass", ap_pass);
                editor.putString("server_ip", server_ip);
                editor.putString("server_port", sever_port);
                editor.putBoolean("ssl_enable", ssl_enable);
                editor.putBoolean("datamode_enable", datamode_enable);
                editor.commit();

                Toast.makeText(getActivity().getApplicationContext(), "Save information", Toast.LENGTH_LONG).show();

            }
        });

        getPreferencesData();

        return view;
    }


    private void getPreferencesData() {

        WizFi250_SSID.setText(mPref.getString("wizfi250_ssid_pre", "WizFi250_AP_"));
        WizFi250_PASS.setText(mPref.getString("wizfi250_pass", "123456789"));
        WizFi250_AIRCMD_IP.setText(mPref.getString("wizfi250_aircmd_ip", "192.168.12.1"));
        WizFi250_AIRCMD_PORT.setText(mPref.getString("wizfi250_aircmd_port", "5000"));
        AP_SSID.setText(mPref.getString("ap_ssid", "iptime"));
        AP_PASS.setText(mPref.getString("ap_pass", "123456789"));

        SERVER_IP.setText(mPref.getString("server_ip", "192.168.1.2"));
        SERVER_PORT.setText(mPref.getString("server_port", "5000"));
        SSL_ENABLE.setChecked(mPref.getBoolean("ssl_enable", false));
        DATAMODE_ENABLE.setChecked(mPref.getBoolean("datamode_enable", false));

    }
}