package ec.edu.pucem.chris.com.Formularios;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;


import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ec.edu.pucem.chris.com.dominios.Prefecto;

public class CrearPrefecto extends JInternalFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;

	private Prefecto prefecto;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JButton btnCancelar;


	
	private List<Prefecto> prefectos;
	private int idPrefecto;
	
	public CrearPrefecto(List<Prefecto> prefectos) {
		getContentPane().setBackground(new Color(222, 239, 224));
		idPrefecto=1;
		this.prefectos=prefectos;
		setTitle("CANDIDATOS A PREFECTO");
		setBounds(100, 100, 443, 385);
		getContentPane().setLayout(null);

		JLabel lblNombres = new JLabel("Nombres");
		lblNombres.setBounds(30, 25, 70, 15);
		getContentPane().add(lblNombres);

		txtNombre = new JTextField();
		txtNombre.addActionListener(this);
		txtNombre.setBounds(97, 23, 231, 19);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(this);
		btnNuevo.setBounds(30, 72, 117, 25);
		getContentPane().add(btnNuevo);

		btnGuardar = new JButton("Agregar");
		btnGuardar.addActionListener(this);
		btnGuardar.setBounds(157, 72, 117, 25);
		getContentPane().add(btnGuardar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(286, 72, 117, 25);
		getContentPane().add(btnCancelar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 115, 416, 224);
		getContentPane().add(scrollPane);
		
		
		

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(model.getValueAt(0, 0));
			}
		});
		
		txtNombre.addKeyListener(new KeyAdapter() {
			  public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode() == KeyEvent.VK_ENTER) {    
			      agregarPrefecto(); 
			    }
			  }
			});

		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nombre" }));
		scrollPane.setViewportView(table);

		model = (DefaultTableModel) table.getModel();
		agregarFila();
	}

	private void nuevo() {
		prefecto = new Prefecto();
		txtNombre.setText(prefecto.getNombre());
	}

	private void agregarPrefecto() {
		String nombre = txtNombre.getText();
		for (Prefecto pre : prefectos) {
			if (pre.getNombre().equalsIgnoreCase(nombre)) {
				JOptionPane.showMessageDialog(null,"NOMBRE YA EXISTE");
				return;
			}
		}

		prefecto = new Prefecto();
		prefecto.setNombre(nombre);
		prefecto.setId(idPrefecto);
		prefecto.setNombre(txtNombre.getText());
		prefectos.add(prefecto);
		agregarFila();
		txtNombre.setText("");
		idPrefecto++;
		
		
		
		
	}
	
	

	private void agregarFila() {
        model.setRowCount(0);
        for (Prefecto prefecto : prefectos) {
            Object[] fila = new Object[1];
            fila[0] = prefecto.getNombre();

            if(prefecto.getNombre().isEmpty()) {

            }
            else {
                model.addRow(fila);
            }

        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNuevo) {
			nuevo();
		} else if (e.getSource() == btnGuardar) {
			agregarPrefecto();
		} else if (e.getSource() == btnCancelar) {
			dispose();
		} 
		
	}

	public List<Prefecto> getPrefectos() {
		return prefectos;
	}

	public void setPrefectos(List<Prefecto> prefectos) {
		this.prefectos = prefectos;
	}

}
