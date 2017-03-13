package com.example.android.justjava;
/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;



    // Check what is that int for
    int myVariable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    // This should be a method for saving the state in Landscape mode. But it's not working
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("myKey", myVariable);
    }

    // This should be a method for saving the state in Landscape mode. But it's not working
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        myVariable = savedInstanceState.getInt("myKey");
    }


    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }


    public void decrement(View view){
        quantity = quantity - 1;
        displayQuantity(quantity);
    }



    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        Log.v("MainActivity", "Has whipped cream: " + hasWhippedCream);

        // If statement
        if (hasWhippedCream) {

            Log.v("MainActivity", "Add $1 to the Total price");
        } else {
            calculatePrice();
            Log.v("MainActivity", "No additional amount added to the Total price.");
        }

        // Figure out if the user wants chocolate topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check_box);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        Log.v("MainActivity", "Has Chocolate: " + hasChocolate);

        EditText nameField = (EditText) findViewById(R.id.name_field);
        String yourName = nameField.getText().toString();

        int price = calculatePrice();

        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, yourName);
        displayMessage(priceMessage);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice(){
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check_box);
        if (whippedCreamCheckBox.isChecked() && (chocolateCheckBox.isChecked())){
            return quantity * (5 + 1 + 2);
        } else if ((whippedCreamCheckBox.isChecked()) && (!chocolateCheckBox.isChecked())){
            return quantity * (5 + 1);
        } else if ((!whippedCreamCheckBox.isChecked()) && (chocolateCheckBox.isChecked())){
            return quantity * (5 + 2);
        } else {
            return quantity * 5;
        }

    }




    /**
     * Create summary of the order.
     *
     * @param price of the order
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @param addChocolate is whether or not the user wants chocolate topping
     * @param addName is the name of the user
     * @return text summary
     */
    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String addName) {

        String priceMessage = "Name: " + addName;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage += "\nQuantity:" + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }


}