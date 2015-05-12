/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icp.icp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Wandy
 */
@Controller
public class KaryawanController {
    
    @RequestMapping(value="karyawan/input", method = RequestMethod.GET)
    public String insert()
    {
        return "karyawan";
    }
    
    @RequestMapping(value={"karyawan", ""}, method = RequestMethod.GET)
    public String loadAll()
    {
        return "daftar_karyawan";
    }

}
