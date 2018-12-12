package pt.ulisboa.tecnico.sirs.api.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Properties;

import org.apache.maven.shared.utils.cli.javatool.JavaToolException;
import org.bouncycastle.operator.OperatorCreationException;
import org.codehaus.mojo.keytool.DefaultKeyTool;
import org.codehaus.mojo.keytool.KeyTool;
import org.codehaus.mojo.keytool.requests.KeyToolGenerateKeyPairRequest;
import org.springframework.core.io.ClassPathResource;

public class KeyUtils {

    private KeyUtils(){}
    private static final String KEYSTORE_FILE_PATH = "src/main/resources/";
    private static final String KEYSTORE_FILE_NAME = "citizens.jks";
    private static final String PASSWORD_FILE_NAME = "application.properties";

    public static void createKeyPair(String citizenId) throws IOException, InterruptedException, JavaToolException {

        String pwd = getPwd();
        String keyStoreFile = KEYSTORE_FILE_PATH + KEYSTORE_FILE_NAME;
        
        KeyToolGenerateKeyPairRequest request = new KeyToolGenerateKeyPairRequest();
        request.setAlias(citizenId);
        request.setKeystore(keyStoreFile);
        request.setStorepass(pwd);
        request.setValidity("365");
        request.setKeysize("2048");
        request.setSigalg("SHA256withRSA");
        request.setKeyalg("RSA");
        request.setDname("CN=" + citizenId);
        request.setKeypass(pwd);
        request.setExt("bc:c=ca:false");
        request.setStoretype("pkcs12");
        
        DefaultKeyTool keyTool = new DefaultKeyTool();
        System.out.print(keyTool.execute(request).getCommandline().toString());
        
        /*
        Process p = Runtime.getRuntime().exec("keytool -genkeypair -alias " + citizenId + " -keystore " + keyStoreFile
                + " -storepass " + pwd + " -validity 365 -keysize 2048 -sigalg SHA256withRSA -keyalg RSA -dname "
                + "CN=" + citizenId + " -noprompt -keypass " + pwd + " -ext bc:c=ca:false -storetype pkcs12");
        p.waitFor();
        */
    }

    private static KeyStore getKeystore(String pwd) throws KeyStoreException, IOException, NoSuchAlgorithmException,
            CertificateException {
        ClassPathResource keystoreResource = new ClassPathResource(KEYSTORE_FILE_NAME);
        InputStream keyStoreIS = keystoreResource.getInputStream();
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(keyStoreIS, pwd.toCharArray());
        return keyStore;
    }

    private static String getPwd() throws IOException {
        Properties pwdProps = new Properties();
        ClassPathResource pwdResource = new ClassPathResource(PASSWORD_FILE_NAME);
        InputStream pwdIS = pwdResource.getInputStream();
        pwdProps.load(pwdIS);
        String pwd = pwdProps.getProperty("key-store-password");
        return pwd;
    }

    public static KeyPair getKeyPair(String citizenId) throws IOException, KeyStoreException,
            NoSuchAlgorithmException, CertificateException, UnrecoverableEntryException {

        String pwd = getPwd();
        KeyStore keyStore = getKeystore(pwd);

        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry)keyStore
                .getEntry(citizenId, new KeyStore.PasswordProtection(pwd.toCharArray()));

        RSAPrivateKey privateKey = (RSAPrivateKey) privateKeyEntry.getPrivateKey();
        RSAPublicKey publicKey = (RSAPublicKey) privateKeyEntry.getCertificate().getPublicKey();
        KeyPair keyPair = new KeyPair(publicKey, privateKey);

        return keyPair;
    }

}