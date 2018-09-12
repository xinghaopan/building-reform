package com.cc.buildingReform.controller;

import com.cc.buildingReform.Annotation.Permissions;
import com.cc.buildingReform.Common.Common;
import com.cc.buildingReform.form.*;
import com.cc.buildingReform.service.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/info")
public class InfoRestController {
    @Autowired
    private InfoService infoService;


    private static Logger log = LoggerFactory.getLogger(InfoRestController.class);

    /**
     * 保存农户信息 2018-09-12 by p
     *
     * @param info
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Permissions(target = "loginUser", isJSON = true)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody ResultMessage save(@ModelAttribute("info") Info info,
                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResultMessage result = new ResultMessage();

        try {
            User user = (User) request.getSession().getAttribute("loginUser");

            CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            commonsMultipartResolver.setDefaultEncoding("utf-8");
            if (commonsMultipartResolver.isMultipart(request)) {
                String path = request.getSession().getServletContext().getRealPath("/");

                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

                MultipartFile file = multiRequest.getFile("personImageU");
                if (file != null) {
                    String r = acceptImage(file, path);
                    if ("".equals(r)) {
                        return result.setMessage("");
                    }
                    info.setPersonImage(r);
                }

                file = multiRequest.getFile("acceptanceImage1U");
                if (file != null) {
                    String r = acceptImage(file, path);
                    if ("".equals(r)) {
                        return result.setMessage("");
                    }
                    info.setAcceptanceImage(r);
                }

                file = multiRequest.getFile("acceptanceImage2U");
                if (file != null) {
                    String r = acceptImage(file, path);
                    if ("".equals(r)) {
                        return result.setMessage("r");
                    }
                    info.setAcceptanceImage2(r);
                }

                file = multiRequest.getFile("acceptanceImage3U");
                if (file != null) {
                    String r = acceptImage(file, path);
                    if ("".equals(r)) {
                        return result.setMessage("");
                    }
                    info.setAcceptanceImage3(r);
                }

                file = multiRequest.getFile("fundSendImageU");
                if (file != null) {
                    String r = acceptImage(file, path);
                    if ("".equals(r)) {
                        return result.setMessage("");
                    }
                    info.setFundSendImage(r);
                }

                file = multiRequest.getFile("houseOldImageU");
                if (file != null) {
                    String r = acceptImage(file, path);
                    if ("".equals(r)) {
                        return result.setMessage("");
                    }
                    info.setHouseOldImage(r);
                }

                file = multiRequest.getFile("houseBuildingImageU");
                if (file != null) {
                    String r = acceptImage(file, path);
                    if ("".equals(r)) {
                        return result.setMessage("");
                    }
                    info.setHouseBuildingImage(r);
                }

                file = multiRequest.getFile("houseOutNewImageU");
                if (file != null) {
                    String r = acceptImage(file, path);
                    if ("".equals(r)) {
                        return result.setMessage("");
                    }
                    info.setHouseOutNewImage(r);
                }

                file = multiRequest.getFile("houseInNewImageU");
                if (file != null) {
                    String r = acceptImage(file, path);
                    if ("".equals(r)) {
                        return result.setMessage("");
                    }
                    info.setHouseInNewImage(r);
                }

                file = multiRequest.getFile("delegaterImageU");
                if (file != null) {
                    String r = acceptImage(file, path);
                    if ("".equals(r)) {
                        return result.setMessage("");
                    }
                    info.setPersonDelegateImage(r);
                }
            }

            info.setPersonId(info.getPersonId().toUpperCase());
            info.setDepartmentId(user.getDepartmentId());
            info.setDepartmentName(user.getDepartmentName());
            info.setUserId(user.getId());
            info.setUserName(user.getTrueName());
            infoService.maintain(info);
        } catch (Exception e) {

        }

        return result;
    }

    @Permissions(target = "loginUser", isJSON = true)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ResultMessage del(@PathVariable("id") Integer id,
                                            HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResultMessage result = new ResultMessage();

        try {
            User user = (User) request.getSession().getAttribute("loginUser");
        }
        catch (Exception e) {

        }

        return result;
    }
    private String acceptImage(MultipartFile file, String path) {
        try {
            String ext = Common.getExtensionName(file.getOriginalFilename());
            if ("".equals(ext)) {
                return "";
            }

            String fileName = "/uploads/" + new Date().getTime() + "." + ext;
            Common.zoomImageScale(file.getInputStream(), path + fileName, 500);
            return fileName;
        }
        catch (Exception e) {
            return "";
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor dateEditor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

}
