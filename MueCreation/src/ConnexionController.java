import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ConnexionController {
	
	Fonction f = new Fonction(this);
	
	//Control (Textbox)
	@FXML public TextField Tb_Username;
	@FXML public TextField Tb_Password;
	//Control (Bouton)
	@FXML public Button Btn_Connexion;
	@FXML public Button Btn_Inscription;
	//Control(Hyperlink)
	@FXML public Hyperlink HL_ForgotPassword;
	//Control(ImageView)
	@FXML public ImageView Img_Close;
	@FXML public ImageView Img_User;
	@FXML public ImageView Img_Password;
	//Control(Label)
	@FXML public Label LblMessage;
	
		File file;
		Image icn;
	public void initialize() {	
		//Image signe dollars
		file = new File("src\\Photos\\X30.png");
		icn = new Image(file.toURI().toString());
		Img_Close.setImage(icn);
	
		file = new File("src\\Photos\\User30.png");
		icn = new Image(file.toURI().toString());
		Img_User.setImage(icn);
	
		file = new File("src\\Photos\\lock30.png");
		icn = new Image(file.toURI().toString());
		Img_Password.setImage(icn);
	}   
	
	@FXML
    void ActionPrincipal(ActionEvent event) {
    	 Button btn =(Button)event.getSource();
    	 String ID = "";
  	   	 ID = btn.getId();
  	   	 switch(ID) {
  	   	 		case "Btn_Connexion":
  	   	 			f.Connexion();
  	   	 			break;
  	   	 		case "Btn_Inscription":
  	   	 			
  	   	 			break;
  	   	 			
  	   	 }
    }
	@FXML
	void OnMouseClose(MouseEvent event) {
		System.exit(0);
	}
	
}
