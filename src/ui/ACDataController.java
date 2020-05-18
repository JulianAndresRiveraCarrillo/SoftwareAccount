package ui;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Account;
import model.BalanceSheet;
import model.Data;
import model.Patrimony;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class ACDataController {

	Data data;

	private ObservableList<Account> accounts;
	private ObservableList<Account> adjustments;
	private ObservableList<Patrimony> patrimonies;
	Image img;

	ObservableList<Account> ac;

	BalanceSheet balance;

	InputStream imgInputStream;
	
	@FXML
	private Pane paneAdjustment;

	@FXML
	private TextField tfValueAdjustment;

	@FXML
	private TextField tfCuentaAdjustment;

	@FXML
	private TableView<Account> tvAdjustment;

	@FXML
	private TableColumn<Account, String> columnCuentaAdj;

	@FXML
	private TableColumn<Account, Long> columnValueAjd;

	@FXML
	private TableColumn<Account, String> columnAdjustment;

	@FXML
	private TableColumn<Account, Long> columnTotal;

	@FXML
	private TextField tfAdjustment;

	@FXML
	private TextField tfValueCuentaAdj;

	@FXML
	private TextField tfPatrimonio;

	@FXML
	private TextField tfValorPat;

	@FXML
	private TextField tfcompanyName;

	@FXML
	private TextField tfInitialPeriod;

	@FXML
	private TextField tfFinalPeriod;

	@FXML
	private TextField tfNIT;

	@FXML
	private Label tagName;

	@FXML
	private Label tagInitial;

	@FXML
	private Label tagFinal;

	@FXML
	private Pane panePatrimonio;

	@FXML
	private Pane paneData;

	@FXML
	private Pane paneMainMenu;

	@FXML
	private ComboBox<String> cbSubtipo;

	@FXML
	private ComboBox<String> cbCuenta;

	@FXML
	private TextField tfNombre;
	
	@FXML
    private ImageView imgviewLogo;
	
	@FXML
    private ImageView imgPat;

	@FXML
	private TextField tfValor;

	@FXML
	private Button btGuardar;

	@FXML
	private Button btAgregar;

	@FXML
	private ComboBox<String> cbTipo;

	@FXML
	private TableView<Account> tvDatos;

	@FXML
	private TableView<Patrimony> tvPatrimony;

	@FXML
	private TableColumn<Account, Character> columnTipo;

	@FXML
	private TableColumn<Account, Long> columnValor;

	@FXML
	private TableColumn<Account, String> columnCuenta;

	@FXML
	private TableColumn<Patrimony, Long> columnValue;

	@FXML
	private TableColumn<Patrimony, String> columnPatrimony;

	long totalActivosC = 0;
	long totalActivosNC = 0;
	long totalPasivosC = 0;
	long totalPasivosNC = 0;

	@FXML
	void openDataMenu(ActionEvent event) {
		paneMainMenu.setVisible(false);
		paneData.setVisible(true);
		panePatrimonio.setVisible(false);
		paneAdjustment.setVisible(false);
	}

	@FXML
	void openAccountMenu(ActionEvent event) {
		paneMainMenu.setVisible(true);
		paneData.setVisible(false);
		panePatrimonio.setVisible(false);
		paneAdjustment.setVisible(false);
	}

	@FXML
	void openPatrimonyMenu(ActionEvent event) {
		paneMainMenu.setVisible(false);
		paneData.setVisible(false);
		panePatrimonio.setVisible(true);
		paneAdjustment.setVisible(false);
	}

	@FXML
	void Ajustes(ActionEvent event) {
		paneMainMenu.setVisible(false);
		paneData.setVisible(false);
		panePatrimonio.setVisible(false);
		paneAdjustment.setVisible(true);
		adjustments = accounts;
		tvAdjustment.setItems(adjustments);
	}
	
	@FXML
	void doAdjustment(ActionEvent event) {
		Account account = tvAdjustment.getSelectionModel().getSelectedItem();
		if(account!=null) {
			for (int i = 0; i < accounts.size(); i++) {
				if(account.getName().equals(accounts.get(i).getName())) {
					try {
						long adjustment = Long.parseLong(tfValueAdjustment.getText());
						accounts.get(i).setAdjustment(adjustment);
						accounts.get(i).setTotal(accounts.get(i).getValue()+adjustment);
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Por favor ingrese un valor válido","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			adjustments = accounts;
			tvAdjustment.refresh();
		}else {
			JOptionPane.showMessageDialog(null, "Por favor seleccione una cuenta","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	void BalanceDePrueba(ActionEvent event) {
		JOptionPane.showMessageDialog(null, "Se ha generado el reporte del Balance de Prueba");
	}

	@FXML
	void BalanceGeneral(ActionEvent event) {
		if (data != null) {
			if (!accounts.isEmpty()) {
				long totalPatrimony = 0;
				ArrayList<Account> acorriente = getActivosCorrientes();
				ArrayList<Account> acnocorriente = getActivosNoCorrientes();
				ArrayList<Account> pcorriente = getPasivosCorrientes();
				ArrayList<Account> pnocorriente = getPasivosNoCorrientes();
				ArrayList<Account> ioperativo = getIngresosOperativos();
				ArrayList<Account> inoOperativo = getIngresosNoOperativos();
				if(acorriente.isEmpty())
					acorriente.add(new Account("", "", ""));
				if(acnocorriente.isEmpty())
					acnocorriente.add(new Account("", "", ""));
				if(pcorriente.isEmpty())
					pcorriente.add(new Account("", "", ""));
				if(pnocorriente.isEmpty())
					pnocorriente.add(new Account("", "", ""));
				if(patrimonies.isEmpty())
					patrimonies.add(new Patrimony("", 0));
				else {
					for (int i = 0; i < patrimonies.size(); i++) {
						totalPatrimony += patrimonies.get(i).getValue();
					}
				}
				JasperReport reporte;
				String path = "src\\formats\\balancesheet.jasper";
				try {
					Map<String, Object> parameters = new HashMap<String, Object>();
					JRBeanCollectionDataSource ac = new JRBeanCollectionDataSource(acorriente);
					JRBeanCollectionDataSource anc = new JRBeanCollectionDataSource(acnocorriente);
					JRBeanCollectionDataSource pc = new JRBeanCollectionDataSource(pcorriente);
					JRBeanCollectionDataSource pnc = new JRBeanCollectionDataSource(pnocorriente);
					JRBeanCollectionDataSource pat = new JRBeanCollectionDataSource(patrimonies);
					//JRBeanCollectionDataSource io = new JRBeanCollectionDataSource(ioperativo);
					//JRBeanCollectionDataSource ino = new JRBeanCollectionDataSource(inoOperativo);
					
					
					parameters.put("name", data.getCompanyName());
					parameters.put("nit", data.getNIT());
					parameters.put("image", imgInputStream);
					parameters.put("fecha", data.getFinalPeriod());
					parameters.put("ac", ac);
					parameters.put("anc", anc);
					parameters.put("pc", pc);
					parameters.put("pnc", pnc);
					parameters.put("totalActivosC", totalActivosC);
					parameters.put("totalActivosNC", totalActivosNC);
					parameters.put("totalPasivosC", totalPasivosC);
					parameters.put("totalPasivosNC", totalPasivosNC);
					parameters.put("totalActivos", totalActivosC + totalActivosNC);
					parameters.put("totalPasivos", totalPasivosC + totalPasivosNC);
					parameters.put("pat",pat);
					parameters.put("totalPatrimony",totalPatrimony);
					parameters.put("totalPasPat",totalPatrimony + (totalPasivosC + totalPasivosNC));

					reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
					JasperPrint jprint = JasperFillManager.fillReport(reporte, parameters, new JREmptyDataSource());
					JasperViewer viewer = new JasperViewer(jprint, false);
					viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					viewer.setVisible(true);
				} catch (JRException ex) {
					ex.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Se ha generado el reporte del Balance General");
			} else {
				JOptionPane.showMessageDialog(null, "Para generar un balance debe tener cuentas creadas", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Para generar un balance debe haber registrado los datos de la empresa",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	void EstadoDeResultados(ActionEvent event) {
		JOptionPane.showMessageDialog(null, "Se ha generado el reporte del Estado de Resultados");
	}

	public void initialize() {
		paneMainMenu.setVisible(false);
		paneData.setVisible(true);
		panePatrimonio.setVisible(false);
		cbTipo.getItems().addAll("Activo", "Pasivo", "Ingreso", "Gasto");
		accounts = FXCollections.observableArrayList();
		adjustments = FXCollections.observableArrayList();
		patrimonies = FXCollections.observableArrayList();
		ac = FXCollections.observableArrayList();
		columnTipo.setCellValueFactory(new PropertyValueFactory<>("type"));
		columnValor.setCellValueFactory(new PropertyValueFactory<>("value"));
		columnCuenta.setCellValueFactory(new PropertyValueFactory<>("name"));
		columnPatrimony.setCellValueFactory(new PropertyValueFactory<>("patrimony"));
		columnValue.setCellValueFactory(new PropertyValueFactory<>("value"));
		columnCuentaAdj.setCellValueFactory(new PropertyValueFactory<>("name"));
		columnValueAjd.setCellValueFactory(new PropertyValueFactory<>("value"));
		columnAdjustment.setCellValueFactory(new PropertyValueFactory<>("adjustment"));
		columnTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

		cbTipo.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				cbSubtipo.getItems().clear();
				if (newValue != null) {
					if (newValue.equals(Account.ACTIVO) || newValue.equals(Account.PASIVO)) {
						cbSubtipo.getItems().addAll(Account.CORRIENTE, Account.NO_CORRIENTE);
					} else if (newValue.equals(Account.INGRESO) || newValue.equals(Account.GASTO)) {
						cbSubtipo.getItems().addAll(Account.OPERATIVO, Account.NO_OPERATIVO);
					}
				}
			}

		});
		
		 tvAdjustment.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Account>() {

			@Override
			public void changed(ObservableValue<? extends Account> observable, Account oldValue, Account newValue) {
				Account account = tvAdjustment.getSelectionModel().getSelectedItem();
				tfCuentaAdjustment.setText(account.getName());
				tfValueCuentaAdj.setText(String.valueOf(account.getValue()));
			}
		});

	}

	@FXML
	void guardarCuentaCreada(ActionEvent event) {

		String type = cbTipo.getSelectionModel().getSelectedItem();
		String subtype = cbSubtipo.getSelectionModel().getSelectedItem();
		String name = tfNombre.getText();
		Account acco = new Account(name, type, subtype);
		accounts.add(acco);
		cbCuenta.getItems().add(acco.getName());

		cbTipo.getSelectionModel().clearSelection();
		tfNombre.setText("");
	}

	@FXML
	void agregarCuenta(ActionEvent event) {
		String item = cbCuenta.getSelectionModel().getSelectedItem();
		try {
			for (int i = 0; i < accounts.size(); i++) {
				if (accounts.get(i).getName().equals(item)) {
					accounts.get(i).setValue(Integer.parseInt(tfValor.getText()));
					setList(accounts.get(i));
				}
			}
			cbCuenta.getItems().remove(item);
			cbCuenta.getSelectionModel().clearSelection();
			tfValor.setText("");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Ingrese un número válido");
		}
	}

	public void setList(Account account) {
		ac.add(account);
		tvDatos.setItems(ac);
	}

	@FXML
	void loadData(ActionEvent event) {

		String name, initialP, finalP;
		name = tfcompanyName.getText();
		initialP = tfInitialPeriod.getText();
		finalP = tfFinalPeriod.getText();

		if (name.equals(""))
			tagName.setVisible(true);
		else
			tagName.setVisible(false);

		if (initialP.equals(""))
			tagInitial.setVisible(true);
		else
			tagInitial.setVisible(false);

		if (finalP.equals(""))
			tagFinal.setVisible(true);
		else
			tagFinal.setVisible(false);

		if (!name.equals("") && !initialP.equals("") && !finalP.equals("")) {
			data = new Data(tfcompanyName.getText(), tfNIT.getText(), tfInitialPeriod.getText(),
					tfFinalPeriod.getText(), img);
			JOptionPane.showMessageDialog(null, "Datos guardados");
		} else {
			JOptionPane.showMessageDialog(null, "Debe diligenciar todos los campos obligatorios", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	void loadImage(ActionEvent event) throws MalformedURLException {
		try {
			FileChooser fc = new FileChooser();
			fc.getExtensionFilters().add(new ExtensionFilter("PNG","*.png","JPG","*.jpg"));
			File f = fc.showOpenDialog(null);
			if (f != null) {
				img = new Image(f.toURI().toURL().toString());
				imgviewLogo.setImage(img);
				imgInputStream = this.getClass().getResourceAsStream(f.getAbsolutePath());
				imgPat.setImage(img);
			}
				
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void deleteImage(ActionEvent event) {
		imgviewLogo.setImage(null);
		imgPat.setImage(null);
	}

	@FXML
	void addPatrimonio(ActionEvent event) {
		String p = tfPatrimonio.getText();
		try {
			int value = Integer.parseInt(tfValorPat.getText());
			if (!tfPatrimonio.getText().equals("")) {
				if (!tfValorPat.getText().equals("")) {
					patrimonies.add(new Patrimony(p, value));
					tvPatrimony.setItems(patrimonies);
					tfPatrimonio.setText("");
					tfValorPat.setText("");
				} else {

				}
			} else {

			}
		} catch (NumberFormatException e) {

		}
	}

	public ArrayList<Account> getActivosCorrientes() {
		ArrayList<Account> ac = new ArrayList<>();
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getType().equals(Account.ACTIVO)
					&& accounts.get(i).getSubType().equals(Account.CORRIENTE)) {
				ac.add(accounts.get(i));
				totalActivosC += accounts.get(i).getValue();
			}

		}
		return ac;
	}

	public ArrayList<Account> getActivosNoCorrientes() {
		ArrayList<Account> anc = new ArrayList<>();
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getType().equals(Account.ACTIVO)
					&& accounts.get(i).getSubType().equals(Account.NO_CORRIENTE)) {
				anc.add(accounts.get(i));
				totalActivosNC += accounts.get(i).getValue();
			}
		}
		return anc;
	}

	public ArrayList<Account> getPasivosCorrientes() {
		ArrayList<Account> pc = new ArrayList<>();
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getType().equals(Account.PASIVO)
					&& accounts.get(i).getSubType().equals(Account.CORRIENTE)) {
				pc.add(accounts.get(i));
				totalPasivosC += accounts.get(i).getValue();
			}
		}
		return pc;
	}

	public ArrayList<Account> getPasivosNoCorrientes() {
		ArrayList<Account> pnc = new ArrayList<>();
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getType().equals(Account.PASIVO)
					&& accounts.get(i).getSubType().equals(Account.NO_CORRIENTE)) {
				pnc.add(accounts.get(i));
				totalPasivosNC += accounts.get(i).getValue();
			}
		}
		return pnc;
	}

	public ArrayList<Account> getIngresosOperativos() {
		ArrayList<Account> io = new ArrayList<>();
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getType().equals(Account.INGRESO)
					&& accounts.get(i).getSubType().equals(Account.OPERATIVO)) {
				io.add(accounts.get(i));
			}
		}
		return io;
	}

	public ArrayList<Account> getIngresosNoOperativos() {
		ArrayList<Account> ino = new ArrayList<>();
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getType().equals(Account.INGRESO)
					&& accounts.get(i).getSubType().equals(Account.NO_OPERATIVO)) {
				ino.add(accounts.get(i));
			}
		}
		return ino;
	}

	public long calcularUtilidad(long ingresosNoOpera, long ingresosOpera, long costosVenta, long gastosNoOpera,
			long gastosOpera, long gastosInteres, long porcentajeImpuesto) {
		long utilidadBruta = 0;
		long utilidadOperativa = 0;
		long gastoImpuesto = 0;
		long utilidadAntesImpuestoIntereses = 0;
		long utilidadAntesImpuesto = 0;
		long utilidadNeta = 0;

		utilidadBruta = ingresosOpera - costosVenta;
		utilidadOperativa = utilidadBruta - gastosOpera;

		if (gastosInteres != 0) {
			utilidadAntesImpuestoIntereses = (utilidadOperativa + ingresosNoOpera) - gastosNoOpera;
			utilidadAntesImpuesto = utilidadAntesImpuestoIntereses - gastosInteres;
		}

		utilidadAntesImpuesto = (utilidadOperativa + ingresosNoOpera) - gastosNoOpera;
		gastoImpuesto = utilidadAntesImpuesto * porcentajeImpuesto;

		utilidadNeta = utilidadAntesImpuesto - gastoImpuesto;

		return utilidadNeta;
	}
}
