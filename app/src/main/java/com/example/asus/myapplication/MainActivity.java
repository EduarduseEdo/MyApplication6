package com.example.asus.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    int quantity2 = 0;
    boolean hasWhippedCream=false,hasChocolate=false,hasChocoLoco=false,hasAlcapone=false,hasGreenTea=false;
    int PriceKopi,PriceDonat,PRICE;
    private long backPressedTime;
    private Toast backToast;


    @Override
    public void onBackPressed() {
        if (backPressedTime+2000>System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;}else{
            backToast=Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime=System.currentTimeMillis();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"Selamat Datang di Main Layout", Toast.LENGTH_LONG).show();


    }

    public void decrement(View view){
        if (quantity == 0) {
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
        PriceKopi = calculatePriceKopi();
        PRICE = calculatePrice(PriceDonat, PriceKopi);
        displayPriceKopi(PriceKopi);
    }

    public void decrementdonat(View view){
        if (quantity2 == 0) {
            return;
        }
        quantity2 = quantity2 - 1;
        displayQuantity2(quantity2);
        PriceDonat = calculatePriceDonat();
        PRICE = calculatePrice(PriceDonat, PriceKopi);
        displayPriceDonat(PriceDonat);
    }

    public void increment(View view){
        if (quantity == 100) {
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
        PriceKopi = calculatePriceKopi();
        PRICE = calculatePrice(PriceDonat, PriceKopi);
        displayPriceKopi(PriceKopi);
    }

    public void incrementdonat (View view){
        if (quantity2 == 100) {
            return;
        }
        quantity2 = quantity2 + 1;
        displayQuantity2(quantity2);
        PriceDonat = calculatePriceDonat();
        PRICE = calculatePrice(PriceDonat, PriceKopi);
        displayPriceDonat(PriceDonat);
    }

    public void onCheckboxClicked(View view){

        boolean checked = ((CheckBox)view).isChecked();
        switch (view.getId()){
            case R.id.whipped_cream_checkbox:
                if (checked){
                    hasWhippedCream=true;
                    PriceKopi = calculatePriceKopi();
                    PRICE = calculatePrice(PriceDonat, PriceKopi);
                    displayPriceKopi(PriceKopi);
                }else{
                    hasWhippedCream=false;
                    PriceKopi = calculatePriceKopi();
                    PRICE = calculatePrice(PriceDonat,PriceKopi);
                    displayPriceKopi(PriceKopi);
                }break;
            case R.id.chocolate_checkbox:
                if(checked){
                    hasChocolate=true;
                    PriceKopi=calculatePriceKopi();
                    PRICE = calculatePrice(PriceDonat,PriceKopi);
                    displayPriceKopi(PriceKopi);
                }else{
                    hasChocolate=false;
                    PriceKopi = calculatePriceKopi();
                    PRICE = calculatePrice(PriceDonat,PriceKopi);
                    displayPriceKopi(PriceKopi);
                }break;
            case R.id.choco:
                if (checked){
                    hasChocoLoco=true;
                    PriceDonat = calculatePriceDonat();
                    PRICE = calculatePrice(PriceDonat,PriceKopi);
                    displayPriceDonat(PriceDonat);
                }else{
                    hasChocoLoco=false;
                    PriceDonat = calculatePriceDonat();
                    PRICE = calculatePrice(PriceDonat,PriceKopi);
                    displayPriceDonat(PriceDonat);
                }break;
            case R.id.alcapone:
                if(checked){
                    hasAlcapone=true;
                    PriceDonat = calculatePriceDonat();
                    PRICE = calculatePrice(PriceDonat,PriceKopi);
                    displayPriceDonat(PriceDonat);
                }else{
                    hasAlcapone=false;
                    PriceDonat = calculatePriceDonat();
                    PRICE = calculatePrice(PriceDonat,PriceKopi);
                    displayPriceDonat(PriceDonat);
                }break;
            case R.id.green_tea:
                if (checked){
                    hasGreenTea=true;
                    PriceDonat = calculatePriceDonat();
                    PRICE = calculatePrice(PriceDonat,PriceKopi);
                    displayPriceDonat(PriceDonat);
                }else{
                    hasGreenTea=false;
                    PriceDonat = calculatePriceDonat();
                    PRICE = calculatePrice(PriceDonat,PriceKopi);
                    displayPriceDonat(PriceDonat);
                }break;
        }

    }

    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        Editable nameEditable = nameField.getText();
        String Name = nameEditable.toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        CheckBox chocoCheckBox = (CheckBox) findViewById(R.id.choco);
        CheckBox alcaponeCheckBox = (CheckBox) findViewById(R.id.alcapone);
        CheckBox greenTeaCheckBox = (CheckBox) findViewById(R.id.green_tea);

        hasWhippedCream = whippedCreamCheckBox.isChecked();

        hasChocolate = chocolateCheckBox.isChecked();

        hasChocoLoco = chocoCheckBox.isChecked();

        hasAlcapone = alcaponeCheckBox.isChecked();

        hasGreenTea = greenTeaCheckBox.isChecked();

        PriceKopi = calculatePriceKopi();
        PriceDonat = calculatePriceDonat();
        PRICE = calculatePrice(PriceDonat, PriceKopi);

        String message = createOrderSummary(Name,PriceKopi, PriceDonat, PRICE, hasWhippedCream, hasChocolate, hasAlcapone, hasChocoLoco, hasGreenTea);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto: "));
        intent.putExtra(Intent.EXTRA_SUBJECT,
                getString(R.string.order_summary_email_subject, Name));
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    private int calculatePrice(int priceDonat, int priceKopi) {
        return priceDonat + priceKopi;
    }


    private int calculatePriceKopi(){
        int basePrice = 5000;
        if (hasWhippedCream){
            basePrice = basePrice + 3000;
        }
        if (hasChocolate){
            basePrice = basePrice + 2000;
        }
        return quantity * basePrice;
    }

    private int calculatePriceDonat(){
        int basePrice = 5000;
        if (hasChocoLoco){
            basePrice = basePrice + 3000;
        }

        if (hasAlcapone){
            basePrice = basePrice + 2000;
        }

        if (hasGreenTea){
            basePrice = basePrice + 2000;
        }
        return quantity2 * basePrice;
    }


    private String createOrderSummary(String Name, int price, int priceDonat, int PRICE, boolean addWhippedCream, boolean addChocolate, boolean addChocoLoco, boolean addAlcapone, boolean addGreenTea){
        String priceMessage = getString(R.string.order_summary_name, Name);
        priceMessage += "\n" + getString(R.string.order_coffe);
        priceMessage += "\n" + getString(R.string.order_summary_whipped_cream, addWhippedCream);
        priceMessage += "\n" + getString(R.string.order_summary_chocolate, addChocolate);
        priceMessage += "\n" + getString(R.string.order_summary_quantity, quantity);
        priceMessage += "\n" + getString(R.string.order_donat);
        priceMessage += "\n" + getString(R.string.order_summary_choco, addChocoLoco);
        priceMessage += "\n" + getString(R.string.order_summary_alcapone, addAlcapone);
        priceMessage += "\n" + getString(R.string.order_summary_green_tea, addGreenTea);
        priceMessage += "\n" + getString(R.string.order_summary_quantity2, quantity2);
        priceMessage += "\n" + getString(R.string.order_summary_price,
                NumberFormat.getCurrencyInstance().format(PRICE));
        priceMessage += "\n" + getString(R.string.thank_you);
        return priceMessage;
    }

    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity);
        quantityTextView.setText("" + numberOfCoffees);
    }

    private void displayQuantity2(int numberOfDougnuts) {
        TextView quantity2TextView = (TextView) findViewById(
                R.id.quantity2);
        quantity2TextView.setText("" + numberOfDougnuts);
    }

    private void displayPriceKopi(int number) {
        TextView PriceKopiTextView = (TextView) findViewById(R.id.pricekopi);
        PriceKopiTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    private void displayPriceDonat(int number) {
        TextView PriceDonatTextView = (TextView) findViewById(R.id.pricedonat);
        PriceDonatTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}