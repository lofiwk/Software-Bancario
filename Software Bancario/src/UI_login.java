import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UI_login {
    private JTextField textFieldUsuario;
    private JPasswordField passwordField;
    private JButton ingresarButton;
    private JPanel panelPrincipalLogin;

    private Map<String, String> credenciales;
    private LoginListener loginListener;

    public UI_login() {
        cargarCredenciales();

        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = textFieldUsuario.getText();
                String password = new String(passwordField.getPassword());

                if (validarCredenciales(usuario, password)) {
                    if (loginListener != null) {
                        loginListener.onLoginSuccess();
                    }
                } else {
                    JOptionPane.showMessageDialog(panelPrincipalLogin, "Credenciales inválidas. Inténtelo de nuevo.");
                }
            }
        });
    }

    public JPanel getPanelPrincipalLogin() {
        return panelPrincipalLogin;
    }

    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    private void cargarCredenciales() {
        credenciales = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("archivos/credenciales.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] partes = line.split(":");
                if (partes.length == 2) {
                    credenciales.put(partes[0], partes[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validarCredenciales(String usuario, String password) {
        return credenciales.containsKey(usuario) && credenciales.get(usuario).equals(password);
    }

    public interface LoginListener {
        void onLoginSuccess();
    }
}
