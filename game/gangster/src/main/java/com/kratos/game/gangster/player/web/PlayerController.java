package com.kratos.game.gangster.player.web;

import com.kratos.game.gangster.aop.PrePermissions;
import com.kratos.game.gangster.player.message.ReqPlayerWechatLogin;
import com.kratos.game.gangster.player.message.ResPlayerLogin;
import com.kratos.game.gangster.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/player")
@PrePermissions
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    /**
     * 登陆
     */
    @PostMapping("/login")
    @PrePermissions(required = false)
    public ResponseEntity<ResPlayerLogin> login(@RequestBody ReqPlayerWechatLogin request) {
        ResPlayerLogin resPlayerLogin = new ResPlayerLogin();
        resPlayerLogin.setCreate(playerService.login(request.getCode()));
        return new ResponseEntity<>(resPlayerLogin, HttpStatus.OK);
    }
}
