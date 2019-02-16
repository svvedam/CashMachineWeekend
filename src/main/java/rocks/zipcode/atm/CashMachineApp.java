package rocks.zipcode.atm;

import javafx.scene.control.*;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {

    private Label labelSetAccountID = new Label("Enter Account ");
    
    private TextField textSetAccountID = new TextField();
    Button btnSubmit = new Button("Set Account ID");

    private TextField textDepositAmount = new TextField();
    private Label labelDepositAmount = new Label("Deposit Amount");
    Button btnDeposit = new Button("Deposit");


    private TextField textWithdrawalAmount = new TextField();
    private Label labelWithdrawalAmount = new Label("Withdrawal Amount");
    Button btnWithdraw = new Button("Withdraw");

    Button btnExit = new Button("Exit");


    private CashMachine cashMachine = new CashMachine(new Bank());

    private Parent createContent() {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 600);

        TextArea areaInfo = new TextArea();

        btnDeposit.setDisable(true);
        btnWithdraw.setDisable(true);
        btnExit.setDisable(true);

        btnSubmit.setOnAction(e -> {
            int id = Integer.parseInt(textSetAccountID.getText());
            cashMachine.login(id);

            areaInfo.setText(cashMachine.toString());
            btnDeposit.setDisable(false);
            btnWithdraw.setDisable(false);
            btnExit.setDisable(false);

        });

        btnDeposit.setOnAction(e -> {
            double depositAmount = Double.parseDouble(textDepositAmount.getText());
            cashMachine.deposit(depositAmount);

            areaInfo.setText(cashMachine.toString());
        });


        btnWithdraw.setOnAction(e -> {
            double withdrawalAmount = Double.parseDouble(textWithdrawalAmount.getText());
            cashMachine.withdraw(withdrawalAmount);

            areaInfo.setText(cashMachine.toString());
        });


        btnExit.setOnAction(e -> {
            cashMachine.exit();

            areaInfo.setText(cashMachine.toString());
        });

        FlowPane flowpane = new FlowPane();


        flowpane.getChildren().add(labelSetAccountID);
        flowpane.getChildren().add(textSetAccountID);
        flowpane.getChildren().add(btnSubmit);
        flowpane.getChildren().add(labelDepositAmount);
        flowpane.getChildren().add(textDepositAmount);
        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(labelWithdrawalAmount);
        flowpane.getChildren().add(textWithdrawalAmount);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnExit);
        //vbox.getChildren().addAll(textSetAccountID, flowpane, areaInfo);
        vbox.getChildren().addAll(flowpane, areaInfo);
        return vbox;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
