package bot.controller;

import bot.service.IRestService;
import bot.service.IUserService;
import bot.service.entities.IUser;
import bot.service.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {


    private IRestService iRestService;

    @Autowired
    public void setIRestService(IRestService iRestService) {
        this.iRestService = iRestService;
    }

 @GetMapping(value = "/users/{phoneNumber}")
 public ResponseEntity<IUser> read(@PathVariable(name = "phoneNumber") String phoneNumber) {
     final IUser iUser = iRestService.read(phoneNumber);

     return iUser != null
             ? new ResponseEntity<>(iUser, HttpStatus.OK)
             : new ResponseEntity<>(HttpStatus.NOT_FOUND);
 }

    @GetMapping(value = "/users")
    public ResponseEntity<List<IUser>> read() {
        final List<IUser> users = iRestService.readAll();

        return users != null &&  !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping(value = "/users/{phoneNumber}")
    public ResponseEntity<?> delete(@PathVariable(name = "phoneNumber") String phoneNumber) {
        final boolean deleted = iRestService.delete(phoneNumber);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
