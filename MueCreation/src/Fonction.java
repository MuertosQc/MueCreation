import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Fonction {
	
	ConnexionController cc;
	Login login;
	AccueilController AC;
	public Fonction(ConnexionController cc) {
		this.cc = cc;
	}
	public Fonction(AccueilController AC) {
		this.AC = AC;
	}
	
	private double xOffset = 0;
	private double yOffset = 0;
	
	String RequeteSql; // Utilisé pour tout requête SQL
	Connection ConnexionDB; // Utilisé pour établir connexion
	ResultSet rs;
	
	public void Connexion() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			ConnexionDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/construction", "root", "");
			RequeteSql = "Select * from login where Username=? and Password=?";
			PreparedStatement pst = ConnexionDB.prepareStatement(RequeteSql);
			pst.setString(1, cc.Tb_Username.getText());
			pst.setString(2, cc.Tb_Password.getText());

			rs = pst.executeQuery();

			if (cc.Tb_Username.getText().isEmpty() || cc.Tb_Password.getText().isEmpty()) {
				cc.LblMessage.setVisible(true);
			
				 PauseTransition visiblePause = new PauseTransition(
					        Duration.seconds(3)
					);
					visiblePause.setOnFinished(
					        event -> cc.LblMessage.setVisible(false)
					);
					visiblePause.play();
				
			} else {
				if (rs.next()) {
					// Il manque le rs.getString(2) car on ne récupère pas le mot de passe
					login = new Login(rs.getString(1), rs.getString(2));

					try {
						FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
						Parent root1 = (Parent) fxmlLoader.load();
						AccueilController AC = fxmlLoader.getController();
						//AC.setInfo(login);
						Scene scene = new Scene(root1);
						Stage stage = new Stage();
						stage.getIcons().add(new Image("Photos/M50.png"));
						scene.getStylesheets().add("Style.css");
						stage.initStyle(StageStyle.UNDECORATED);
						root1.setOnMousePressed(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent event) {
								xOffset = event.getSceneX();
								yOffset = event.getSceneY();
							}
						});

						root1.setOnMouseDragged(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent event) {
								stage.setX(event.getScreenX() - xOffset);
								stage.setY(event.getScreenY() - yOffset);
							}
						});
						stage.setScene(scene);
						stage.setTitle("");
//						AC.LblMsgBienvenue.setText("Bienvenue " + AC.NomAdministrateur );
//						TranslateTransition ts = new TranslateTransition(Duration.seconds(1), AC.PaneBienvenue);
//						 ts.setFromY(0);
//						 ts.setToY(-80);
//						 ts.play();
//						 
//						 AC.PaneBienvenue.setVisible(true);
//						 PauseTransition visiblePause = new PauseTransition(
//							        Duration.seconds(3)
//							);
//							visiblePause.setOnFinished(
//							        event -> AC.PaneBienvenue.setVisible(false)
//							);
//							visiblePause.play();
						stage.show();
						Stage stage1 = (Stage) cc.Btn_Connexion.getScene().getWindow();
						stage1.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					

				} else {
					cc.LblMessage.setText("Mauvais mot de passe ou Username");
					 
					 cc.LblMessage.setVisible(true);
					 PauseTransition visiblePause = new PauseTransition(
						        Duration.seconds(3)
						);
						visiblePause.setOnFinished(
						        event -> cc.LblMessage.setVisible(false)
						);
						visiblePause.play();
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
