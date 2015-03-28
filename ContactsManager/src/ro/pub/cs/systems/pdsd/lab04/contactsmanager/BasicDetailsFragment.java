package ro.pub.cs.systems.pdsd.lab04.contactsmanager;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.SyncStateContract.Constants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;

public class BasicDetailsFragment extends Fragment{
	
	Button save, cancel, show;
	
	public class Ascultator implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.bCancel:
					getActivity().finish();
					break;
				case R.id.bSave:
					
					EditText name = (EditText)getActivity().findViewById(R.id.etName);
					EditText phone = (EditText)getActivity().findViewById(R.id.etPhone);
					EditText email = (EditText)getActivity().findViewById(R.id.etEmail);
					EditText address = (EditText)getActivity().findViewById(R.id.etAddress);
					EditText jobTitle = (EditText)getActivity().findViewById(R.id.etJob);
					EditText company = (EditText)getActivity().findViewById(R.id.etCompany);
					
					Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
					intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
					if (name != null) {
					  intent.putExtra(ContactsContract.Intents.Insert.NAME, true);
					}
					if (phone != null) {
					  intent.putExtra(ContactsContract.Intents.Insert.PHONE, true);
					}
					if (email != null) {
					  intent.putExtra(ContactsContract.Intents.Insert.EMAIL, true);
					}
					if (address != null) {
					  intent.putExtra(ContactsContract.Intents.Insert.POSTAL, true);
					}
					if (jobTitle != null) {
					  intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, true);
					}
					if (company != null) {
					  intent.putExtra(ContactsContract.Intents.Insert.COMPANY, true);
					}
					
					getActivity().startActivity(intent);
					
					break;
				case R.id.bShow:
					FragmentManager fm = getActivity().getFragmentManager();
					FragmentTransaction ft = fm.beginTransaction();
					AdditionalDetailsFragment adf = (AdditionalDetailsFragment)fm.findFragmentById(R.id.frameAdditional);
					
					if (adf == null) {
						Button hide = (Button)v;
						hide.setHint("Hide Additional Fields");
						ft.add(R.id.frameAdditional, new AdditionalDetailsFragment());
					}
					else {
						Button sh = (Button)v;
						sh.setHint("Show Additional Fields");
						ft.remove(adf);
					}
					
					ft.commit();
					break;
			}
		}
		
	}
	
	@Override
	public void onActivityCreated(Bundle b) {
		
		super.onActivityCreated(b);
		
		save = (Button)getActivity().findViewById(R.id.bSave);
		cancel = (Button)getActivity().findViewById(R.id.bCancel);
		show = (Button)getActivity().findViewById(R.id.bShow);
		
		Ascultator asculta = new Ascultator();
		
		save.setOnClickListener(asculta);
		show.setOnClickListener(asculta);
		cancel.setOnClickListener(asculta);
		
	}
	
	@Override
	  public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle state) {
		return layoutInflater.inflate(R.layout.fragment_basic_details, container, false);
	  }

}
