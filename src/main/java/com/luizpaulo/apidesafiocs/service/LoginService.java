package com.luizpaulo.apidesafiocs.service;

import com.luizpaulo.apidesafiocs.exception.login.EmailInvalidException;
import com.luizpaulo.apidesafiocs.exception.login.ParametersLoginException;
import com.luizpaulo.apidesafiocs.exception.login.PasswordInvalidException;
import com.luizpaulo.apidesafiocs.util.HashUtil;
import com.luizpaulo.apidesafiocs.vo.LoginVO;
import com.luizpaulo.apidesafiocs.vo.UsuarioResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Service
public class LoginService {

    @Autowired
    private UsuarioService usuarioService;

    public UsuarioResponseVO login(LoginVO loginVO) throws EmailInvalidException, PasswordInvalidException, ParametersLoginException, UnsupportedEncodingException, NoSuchAlgorithmException {

        if (loginVO == null) throw new ParametersLoginException();

        if(usuarioService.getUsuarioVOPorEmail(loginVO.getEmail()) == null)
            throw new EmailInvalidException();

        UsuarioResponseVO usuarioVO = usuarioService.getUsuarioVOPorEmailESenha(loginVO.getEmail(), HashUtil.encripty(loginVO.getPassword()));

        if (usuarioVO == null)
            throw new PasswordInvalidException();

        return usuarioVO;

    }
}
