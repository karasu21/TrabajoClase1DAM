package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import colecciones.Parejas;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class EjemploGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private JTextArea textAreaOrdenada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EjemploGUI frame = new EjemploGUI();
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
	public EjemploGUI() {
		setTitle("Estadistica de palabras");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 500, 450, 264);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Seleccionar Fichero");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lanzarFileChooser();
			}
		});
		btnNewButton.setBounds(10, 29, 156, 23);
		contentPane.add(btnNewButton);

		JLabel lblRuta = new JLabel("Ruta de fichero:");
		lblRuta.setBounds(176, 11, 96, 14);
		contentPane.add(lblRuta);

		textField = new JTextField();
		textField.setBounds(176, 30, 249, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton_1 = new JButton("Palabras Desordenadas");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Map<String, Integer> mapa = estadisticaPalabras(new File(textField.getText()), true);
					String texto = "";
					for (String pal : mapa.keySet()) {
						texto += pal + " " + mapa.get(pal) + "\n";
					}
					textArea.setText(texto);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(10, 65, 167, 23);
		contentPane.add(btnNewButton_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 101, 176, 108);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JButton btnNewButton_2 = new JButton("Palabras Ordenadas");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Map<String, Integer> mapa = estadisticaPalabras(new File(textField.getText()), true);
					Parejas palabras;
					ArrayList<Parejas> cadena = new ArrayList<Parejas>();
					for (String pal : mapa.keySet()) {
						palabras = new Parejas(pal, (Integer) mapa.get(pal));
						cadena.add(palabras);
					}
					Collections.sort(cadena);
					String texto = "";
					for (Parejas pareja : cadena) {
						texto += pareja.getPalabra() + " " + pareja.getRepeticiones() + "\n";
					}
					textAreaOrdenada.setText(texto);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(227, 64, 167, 25);
		contentPane.add(btnNewButton_2);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(229, 101, 196, 108);
		contentPane.add(scrollPane_1);

		textAreaOrdenada = new JTextArea();
		scrollPane_1.setViewportView(textAreaOrdenada);
	}

	protected void lanzarFileChooser() {
		JFileChooser fc = new JFileChooser("/");
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setDialogTitle("Seleccione fichero origen");
		int opcion = fc.showOpenDialog(null);
		if (opcion == JFileChooser.APPROVE_OPTION) {
			textField.setText(fc.getSelectedFile().getAbsolutePath());
		}

	}

	private static Map<String, Integer> estadisticaPalabras(File rutaFich, boolean ignorarMayMin) throws IOException {
		Map<String, Integer> mapa = new TreeMap<String, Integer>();
		BufferedReader bfr = new BufferedReader(new FileReader(rutaFich));
		String linea;
		while ((linea = bfr.readLine()) != null) {
			for (String pal : partirPalabras(linea)) {
				if (ignorarMayMin)
					pal = pal.toUpperCase();
				// A�ade palabra al mapa con contador 1 o incrementando si ya existe
				if (mapa.containsKey(pal))
					mapa.put(pal, mapa.get(pal) + 1);
				else
					mapa.put(pal, 1);
			}
		}

		bfr.close();
		return mapa;
	}

	private static String[] partirPalabras(String linea) {
		return linea.split("[ ,.:;()�!�?]+");
	}
}
