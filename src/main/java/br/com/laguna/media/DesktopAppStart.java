package br.com.laguna.media;

import org.apache.pivot.wtk.DesktopApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import br.com.laguna.media.screen.MainScreen;

@SpringBootApplication
public class DesktopAppStart extends SpringBootServletInitializer {
    public static void main(String[] args) {
	DesktopApplicationContext.main(MainScreen.class, args);
    }
}
