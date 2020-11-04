package bot.web;

import bot.entities.User;
import bot.service.IRestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "telegramBot", description = "operations on telegram bot users")
public class UserController {


    private IRestService iRestService;

    @Autowired
    public void setIRestService(IRestService iRestService) {
        this.iRestService = iRestService;
    }

    @ApiOperation(value = "getting a user by phone number")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the user is successfully obtained"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "the user is not found")
    })
    @GetMapping(value = "/users/{phoneNumber}")
     public ResponseEntity<User> read(@PathVariable(name = "phoneNumber") String phoneNumber) {
     final User iUser = iRestService.read(phoneNumber);

     return iUser != null
             ? new ResponseEntity<>(iUser, HttpStatus.OK)
             : new ResponseEntity<>(HttpStatus.NOT_FOUND);
 }
    @ApiOperation(value = "getting a list of all users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "list of users received successfully"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "No users found")
    })
    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> read() {
        final List<User> users = iRestService.readAll();

        return users != null &&  !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "deleting a user by phone number")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the user was successfully deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "the user is not found")
    })
    @DeleteMapping(value = "/users/{phoneNumber}")
    public ResponseEntity<?> delete(@PathVariable(name = "phoneNumber") String phoneNumber) {
        final boolean deleted = iRestService.delete(phoneNumber);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
