package ec.edu.pucem.chris.com;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import ec.edu.pucem.chris.com.Formularios.BocaDeUrna;
import ec.edu.pucem.chris.com.Formularios.CrearPrefecto;
import ec.edu.pucem.chris.com.dominios.Prefecto;


public class MenuPrincipal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contenedor;
	private JDesktopPane desktopPane;
	private JMenuItem mntmSalir;
	private JMenuItem mntmCrearPrefectos;
	private JMenuItem mntmBocaDeUrna;
	private static boolean windowOpen = false;

	public List<Prefecto> prefectos = new ArrayList<>();
	private JMenuItem mntmResultadosBarras;
	private JMenuItem mntmResultadosPastel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
		setTitle("SISTEMA CONTEO DE VOTOS BOCA DE URNA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(192, 191, 188));
		setJMenuBar(menuBar);

		JMenu mnSistema = new JMenu("Sistema");
		mnSistema.setFont(new Font("Dialog", Font.BOLD, 16));
		menuBar.add(mnSistema);

		mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(this);
		mntmSalir.setFont(new Font("Dialog", Font.BOLD, 16));
		mnSistema.add(mntmSalir);

		JMenu mnAdministracin = new JMenu("Administración");
		mnAdministracin.setFont(new Font("Dialog", Font.BOLD, 16));
		menuBar.add(mnAdministracin);

		mntmCrearPrefectos = new JMenuItem("Crear Prefectos");
		mntmCrearPrefectos.setFont(new Font("Dialog", Font.BOLD, 16));
		mntmCrearPrefectos.addActionListener(this);
		mnAdministracin.add(mntmCrearPrefectos);

		JMenu mnProceso = new JMenu("Proceso");
		mnProceso.setFont(new Font("Dialog", Font.BOLD, 16));
		menuBar.add(mnProceso);

		mntmBocaDeUrna = new JMenuItem("Boca de Urna");
		mntmBocaDeUrna.setFont(new Font("Dialog", Font.BOLD, 16));
		mntmBocaDeUrna.addActionListener(this);
		mnProceso.add(mntmBocaDeUrna);

		mntmResultadosBarras = new JMenuItem("Resultados Barras");
		mntmResultadosBarras.addActionListener(this);
		mntmResultadosBarras.setFont(new Font("Dialog", Font.BOLD, 16));
		mnProceso.add(mntmResultadosBarras);

		mntmResultadosPastel = new JMenuItem("Resultados Pastel");
		mntmResultadosPastel.addActionListener(this);
		mntmResultadosPastel.setFont(new Font("Dialog", Font.BOLD, 16));
		mnProceso.add(mntmResultadosPastel);
		contenedor = new JPanel();
		contenedor.setBackground(new Color(255, 255, 255));
		contenedor.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contenedor);
		contenedor.setLayout(new CardLayout(0, 0));

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(205, 220, 182));
		contenedor.add(desktopPane, "name_35522358088801");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		windowOpen = true;
		if (e.getSource() == mntmSalir) {
			dispose();
		} else if (e.getSource() == mntmCrearPrefectos) {
			CrearPrefecto crearPrefecto = new CrearPrefecto(prefectos);
			desktopPane.add(crearPrefecto);
			crearPrefecto.setVisible(true);
			
			
	
			
			

		} else if (e.getSource() == mntmBocaDeUrna) {
			BocaDeUrna bocaUrna = new BocaDeUrna(prefectos);
			desktopPane.add(bocaUrna);
			bocaUrna.setVisible(true);
		} else if (e.getSource() == mntmResultadosBarras) {
			crearResultadosEnBarras();
		} else if (e.getSource() == mntmResultadosPastel) {
			crearResultadosEnPastel();
		}
	}

	private void cerrarFormulario() {
		  windowOpen = false;
		  this.dispose();
		}
	
	private void crearResultadosEnBarras() {
		windowOpen = true;

		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (Prefecto prefecto : prefectos) {
	        dataset.addValue(prefecto.getVotos(), prefecto.getNombre(), "Category 1");
	    }
		final JFreeChart chart = ChartFactory.createBarChart("Bar Chart", "Category", "Series", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(600, 400));

		final JInternalFrame frame = new JInternalFrame("Resultado en Barras", true);

		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		
		panel.add(btnCancelar, BorderLayout.SOUTH);
	    panel.add(chartPanel, BorderLayout.CENTER);
	    frame.setContentPane(panel);
	    desktopPane.add(frame);
	    frame.pack();
	    frame.setVisible(true);;
	}

	@SuppressWarnings("unused")
	private void addValue() {
		windowOpen = true;


		BocaDeUrna bocaDeUrna = new BocaDeUrna(prefectos);
		bocaDeUrna.setVisible(true);
		for (Prefecto prefecto : prefectos) {
		prefecto.setVotos(prefecto.getVotos() + 1);
		}
		}

	private void crearResultadosEnPastel() {
		windowOpen = true;

		DefaultPieDataset datos = new DefaultPieDataset();
		for (Prefecto prefecto : prefectos) {
			datos.setValue(prefecto.getNombre(), prefecto.getVotos());
		}

		JFreeChart grafico = ChartFactory.createPieChart("Prefectos", datos);
		ChartPanel chartPanel = new ChartPanel(grafico);
		chartPanel.setBounds(10, 50, 450, 350);

		final JInternalFrame frame = new JInternalFrame("Resultado en Pastel", true);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		
		frame.getContentPane().add(btnCancelar, BorderLayout.SOUTH);
		frame.getContentPane().add(chartPanel);
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		
		desktopPane.add(frame);
		frame.pack();
		frame.setVisible(true);
	}
}