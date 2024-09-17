package UmbertoAmoroso.u5s7d1.services;



import UmbertoAmoroso.u5s7d1.exceptions.UnauthorizedException;
import UmbertoAmoroso.u5s7d1.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import UmbertoAmoroso.u5s7d1.entities.Dipendente;
import UmbertoAmoroso.u5s7d1.payloads.UserLoginDTO;


@Service
public class AuthService {

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private JWTTools jwtTools;

    public String checkCredentialsAndGenerateToken(UserLoginDTO body) {
        Dipendente found = this.dipendenteService.findByEmail(body.email());
        if (found != null && found.getPassword().equals(body.password())) {
            return jwtTools.createToken(found);
        } else {
            throw new UnauthorizedException("Credenziali errate!");
        }
    }
}

