package com.express.email;

import com.express.imp.EnviarEmailImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping
public class EnviarEmailControlador {
    @Autowired
    private EnviarEmailImp enviarEmailImp;

    @PostMapping("/enviaremail")
    public String enviarcor(@RequestParam("correo") String correo ,
                            @RequestParam("subject")String subject,@RequestParam("body") String body) {
        String mensaje= body + "\n\n  correo de contacto;" +subject+"\n asunto"  + "\n email" + correo;

        enviarEmailImp.enviaremail("pepelolaso64@gmail.com", correo, subject, body);

        return "respqrs";
    }
}
