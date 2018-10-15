package com.study.riseof.contactbook;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ContactDeleteDialog extends DialogFragment {
    private final String EMPTY_STRING="";
    private final int EMPTY_INDEX = -1;
    private final String TITLE_TEXT="Caution!";
    private final String MESSAGE_TEXT="Delete\n";

    private Unbinder unbinder;
    private int selectedContactId = EMPTY_INDEX;
    private String messageName = EMPTY_STRING;

    DialogClickButtonPositiveListener dialogClickButtonPositiveListener;

    @BindView(R.id.dialog_delete_text_message)
    TextView dialogTextMessage;
    @BindView(R.id.dialog_delete_text_name)
    TextView dialogTextContactName;

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
    }

    protected void onAttachToContext(Context context) {
        try {
            dialogClickButtonPositiveListener  = (DialogClickButtonPositiveListener)context;
        } catch (ClassCastException e) {
            //           Log.d("mylog",context.toString() + " must implement DialogClickButtonPositiveListener");
            throw new ClassCastException(context.toString() + " must implement DialogClickButtonPositiveListener");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_contact_delete, null);
        unbinder = ButterKnife.bind(this, view);
        selectedContactId = getArguments().getInt("selectedContactId");
        ContactBaseManager contactBaseManager = new ContactBaseManager(getContext());
        AbbreviatedContact abbreviatedContact = contactBaseManager.getAbbrContactById(selectedContactId);
        getDialog().setTitle(TITLE_TEXT);
        getDialog().setCancelable(false); //не работает
        dialogTextMessage.setText(MESSAGE_TEXT);
        messageName = abbreviatedContact.getContactName()+" ?";
        dialogTextContactName.setText(messageName);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.dialog_delete_button_positive)
    public void onClickButtonPositive() {
        ContactBaseManager contactBaseManager = new ContactBaseManager(getContext());
        contactBaseManager.deleteContactFromBase(selectedContactId);
        dialogClickButtonPositiveListener.dialogClickButtonPositive();
        getDialog().dismiss();
    }

    @OnClick(R.id.dialog_delete_button_negative)
    public void onClickButtonNegative() {
        getDialog().dismiss();
    }

    public interface DialogClickButtonPositiveListener {
        public void dialogClickButtonPositive();
    }

/*
   // определение диалога через AlertDialog.Builder,
   // не забыть слушателя implements DialogInterface.OnClickListener

    private String dialogText="";
    private final String POSITIVE_BUTTON_TEXT="Yes";
    private final String NEGATIVE_BUTTON_TEXT="No";
    private final String NEUTRAL_BUTTON_TEXT="Cancel";

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        selectedContactId = getArguments().getInt("selectedContactId");
        dialogText = "Удалить\n"+selectedContactId+" ?";
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setTitle("Title!").setPositiveButton(TITLE_TEXT, null)
                .setPositiveButton(POSITIVE_BUTTON_TEXT,this)
                .setNegativeButton(NEGATIVE_BUTTON_TEXT, this)
                .setCancelable(false)
                .setMessage(dialogText);
        return adb.create();
    }

    public void onClick(DialogInterface dialog, int which) {
        int i = 0;
        switch (which) {
            case Dialog.BUTTON_POSITIVE:
                // do
                break;
            case Dialog.BUTTON_NEGATIVE:
                getDialog().dismiss();
                break;
            case Dialog.BUTTON_NEUTRAL:
                // do
                break;
        }
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d("myLog", "Dialog: onDismiss");
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d("myLog", "Dialog: onCancel");
    }
*/
}
