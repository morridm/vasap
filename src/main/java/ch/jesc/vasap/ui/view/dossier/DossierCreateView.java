package ch.jesc.vasap.ui.view.dossier;

import ch.jesc.carenet.core.dao.DossierDAO;
import ch.jesc.vasap.ui.view.BasePanelView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import net.ecozig.web.security.UserSecurity;
import net.ecozig.web.utils.SpringHelper;


public class DossierCreateView extends BasePanelView {

	public static final String NAME = "dossierCreate";

	@Override
	public String getTitle() {
		return "Création d'un dossier";
	}

	@Override
	protected void doNavigateTo(String fragmentParameters) {

		final TextField nom = new TextField();
		layout.addComponent(nom);
		nom.setCaption("Nom");

		final TextField prenom = new TextField();
		layout.addComponent(prenom);
		prenom.setCaption("Prénom");

		final TextField localite = new TextField();
		layout.addComponent(localite);
		localite.setCaption("Localité");

		final Button create = new Button("Créer");
		layout.addComponent(create);
		create.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent clickEvent) {
				getDossierDao().create(UserSecurity.getPrincipal(), nom.getValue(), prenom.getValue(), "", "", 1134, localite.getValue());
				Notification.show("Dossier créé.");

				getNavigator().navigateTo(DossierListView.NAME);
			}
		});
	}

	private DossierDAO getDossierDao() {
		return SpringHelper.getBean(DossierDAO.class);
	}
}
