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
public class InfoController {
    @Autowired
    private InfoService infoService;

    @Autowired
    private DicService dicService;

    @Autowired
    private QuotaService quotaService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private StatisticsQuotaService statisticsQuotaService;

    @Autowired
    private AuditService auditService;

    private static Logger log = LoggerFactory.getLogger(InfoController.class);

    @RequestMapping("/bk/info/exportExcel{path}/{mid}")
    public void exportExcel(@RequestParam(value = "year", required = false) Integer year,
                            @PathVariable(value = "path") String path,
                            HttpServletRequest request, HttpServletResponse response) {
        // 生成提示信息，
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            // 进行转码，使其支持中文文件名
            codedFileName = java.net.URLEncoder.encode("导出农户信息", "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
            // 产生工作簿对象
            HSSFWorkbook workbook = new HSSFWorkbook();
            //产生工作表对象
            HSSFSheet sheet = workbook.createSheet();

            User user = (User) request.getSession().getAttribute("loginUser");

            List<Info> list = new ArrayList<>();
            if ("waitSubmit".equals(path)) {
                list = infoService.findByEdit(year, user);
            } else if ("waitAudit".equals(path)) {
                list = infoService.findByWaitAudit(year, user, 0, 0);
            } else if ("auditInfo".equals(path)) {
                list = infoService.findByAuditInfo(year, user, 0, 0);
            } else if ("backInfo".equals(path)) {
                list = infoService.findByBackInfo(year, user, 0, 0);
            } else if ("otherNoOpen".equals(path)) {
                list = infoService.findByNoOpen(year, user.getDepartmentId(), 0, 0);
            } else if ("otherNoAcceptance".equals(path)) {
                list = infoService.findByNoAcceptance(year, user.getDepartmentId(), 0, 0);
            } else if ("otherNoOver".equals(path)) {
                list = infoService.findByNoOver(year, user.getDepartmentId(), 0, 0);
            } else if ("acceptanceInfo".equals(path)) {
                list = infoService.findByAcceptanceInfo(year, user, 0, 0);
            }

            HSSFRow row;
            HSSFCell cell;
            row = sheet.createRow(0);

            cell = row.createCell((int) 0);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("姓名");

            cell = row.createCell((int) 1);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("身份证号");

            cell = row.createCell((int) 2);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("机构");

            cell = row.createCell((int) 3);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("村民小组");

            cell = row.createCell((int) 4);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("电话");

            for (int i = 0; i < list.size(); i++) {
                Info info = list.get(i);
                row = sheet.createRow((int) i + 1);

                cell = row.createCell((int) 0);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(info.getPersonName());

                cell = row.createCell((int) 1);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(info.getPersonId());

                cell = row.createCell((int) 2);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(info.getSonDepartmentName());

                cell = row.createCell((int) 3);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(info.getPersonGroup());

                cell = row.createCell((int) 4);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(info.getPersonTel());

            }
            fOut = response.getOutputStream();
            workbook.write(fOut);
        } catch (UnsupportedEncodingException e1) {
        } catch (Exception e) {
        } finally {
            try {
                fOut.flush();
                fOut.close();
            } catch (IOException e) {
            }
        }
        System.out.println("文件生成...");
    }

    private String getNavigation(String fatherId, String me, Integer year, Integer mid) {
        String s = "";
        Department department = departmentService.findById(fatherId);
        s = "&nbsp;>&nbsp<a class='mainFrame-first-a' href='/bk/info/list/" + mid + "?year=" + year + "&fatherId=" + fatherId + "'>" + department.getName() + "</a>";
        if (fatherId.equals("01") || me.equals(fatherId)) {

        } else {
            s = getNavigation(department.getQuotaManageId(), me, year, mid) + s;
        }
        return s;
    }

    /**
     * 列表页面 2015-08-05 by p
     *
     * @param model
     * @return
     * @throws Exception
     */
    @Permissions(target = "loginUser", url = "/index")
    @RequestMapping("/bk/info/list{path}/{mid}")
    public String list(@PathVariable("mid") Integer mid,
                       @RequestParam(value = "year", required = false) Integer year,
                       @RequestParam(value = "fatherId", required = false) String fatherId,
                       @RequestParam(value = "currentPage", required = false) Integer currentPage,
                       @RequestParam(value = "count", required = false) Integer count,
                       HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        try {
            User user = (User) request.getSession().getAttribute("loginUser");
            String nav = "";

            if (year == null) {
                year = Quota.getCurrentYear();
            }

            if (currentPage == null) {
                currentPage = 0;
            }

            if (count == null || count == 0) {
                count = 10;
            }

            List<Quota> quotaList = new ArrayList<>();
            List<StatisticsQuota> list = new ArrayList<>();

            if (fatherId == null || fatherId == "") {
                fatherId = user.getDepartmentId();
                quotaList = quotaService.findByDepartmentId(year, fatherId);
                list = statisticsQuotaService.findByDepartmentId(fatherId, year);

            } else {
                quotaList = quotaService.findByDistributeId(fatherId, year);
                list = statisticsQuotaService.findByQuotaManageId(fatherId, year);
                nav = getNavigation(fatherId, user.getDepartmentId(), year, mid);
            }

            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < quotaList.size(); j++) {
                    if (list.get(i).getId().equals(quotaList.get(j).getDepartmentId())) {
                        list.get(i).setQuotaId(quotaList.get(j).getId());
                        list.get(i).setNum(quotaList.get(j).getNum());
                        list.get(i).setRestNum(quotaList.get(j).getRestNum());
                        list.get(i).setDate(quotaList.get(j).getDate());
                        break;
                    }
                }
            }

            model.addAttribute("list", list);

            model.addAttribute("mid", mid);
            model.addAttribute("dicList", dicService.findAll());
            model.addAttribute("year", year);
            model.addAttribute("nav", nav);
            model.addAttribute("user", user);
        } catch (Exception e) {
            log.error("/bk/info/list", e);
        }

        return "/bk/info/list";
    }
//	public String list(@PathVariable("mid") Integer mid, @PathVariable("path") String path, 
//			@RequestParam(value = "year", required = false) Integer year,
//			@RequestParam(value = "currentPage", required = false) Integer currentPage,
//			@RequestParam(value = "count", required = false) Integer count,
//			HttpServletRequest request, Model model) throws Exception {
//		try {
//			User user = (User) request.getSession().getAttribute("loginUser");
//			
//			if (year == null) {
//				year = Quota.getCurrentYear();
//			}
//			
//			if (currentPage == null) {
//				currentPage = 0;
//			}
//			
//			if (count == null || count == 0) {
//				count = 10;
//			}
//			
//			int maxCount = infoService.getCount(year, user);
//			int maxPage = (maxCount - 1) / count + 1;
//			
//			List<Quota> quotaList = quotaService.findByDepartmentId(year, user.getDepartmentId());
//			
//			model.addAttribute("count", count);
//			model.addAttribute("maxPage", maxPage);
//			model.addAttribute("mid", mid);
//			model.addAttribute("path", path);
//			model.addAttribute("user", user);
//			
//			model.addAttribute("list", infoService.findAll(year, user, currentPage * count, count));
//			model.addAttribute("pages", Common.pages(mid, currentPage, maxPage, "", ""));
//			if(quotaList != null && !quotaList.isEmpty()) {
//				model.addAttribute("quota", quotaList.get(0));
//			}
//			model.addAttribute("year", year);
//			model.addAttribute("dicList", dicService.findAll());
//		}
//		catch(Exception e) {
//			log.error("/bk/info/list" + path + "/" + mid, e);
//		}
//		
//		return "/bk/info/list" + path;
//	}

    @Permissions(target = "loginUser", url = "/index")
    @RequestMapping("/bk/info/sublist/{mid}")
    public String sublist(@PathVariable("mid") Integer mid,
                          @RequestParam(value = "year", required = false) Integer year,
                          @RequestParam(value = "fatherId", required = true) String fatherId,
                          @RequestParam(value = "personName", required = false) String personName,
                          @RequestParam(value = "personId", required = false) String personId,
                          @RequestParam(value = "currentPage", required = false) Integer currentPage,
                          @RequestParam(value = "count", required = false) Integer count,
                          @RequestParam(value = "state", required = false) Integer state,
                          HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        try {
            User user = (User) request.getSession().getAttribute("loginUser");
            String nav = getNavigation(fatherId, user.getDepartmentId(), year, mid);

            if (year == null) {
                year = Quota.getCurrentYear();
            }

            if (currentPage == null) {
                currentPage = 0;
            }

            if (count == null || count == 0) {
                count = 10;
            }

            if (state == null) {
                state = 0;
            }

            int maxCount = infoService.getCountByDepartmentId(year, fatherId, personName, personId, state);
            int maxPage = (maxCount - 1) / count + 1;


            model.addAttribute("count", count);
            model.addAttribute("maxPage", maxPage);
            model.addAttribute("mid", mid);
            model.addAttribute("user", user);
            model.addAttribute("fatherId", fatherId);
            model.addAttribute("list", infoService.findByDepartmentId(year, fatherId, personName, personId, state, currentPage * count, count));
            model.addAttribute("pages", Common.pages(mid, currentPage, maxPage, "", ""));
            model.addAttribute("year", year);
            model.addAttribute("state", state);
            model.addAttribute("personName", personName);
            model.addAttribute("personId", personId);
            model.addAttribute("nav", nav);
            model.addAttribute("dicList", dicService.findAll());
        } catch (Exception e) {
            log.error("/bk/info/sublist" + mid, e);
        }

        return "/bk/info/sublist";
    }

    @Permissions(target = "loginUser", url = "/index")
    @RequestMapping("/bk/info/other{path}/{mid}")
    public String other(@PathVariable("mid") Integer mid, @PathVariable("path") String path,
                        @RequestParam(value = "year", required = false) Integer year,
                        @RequestParam(value = "personName", required = false) String personName,
                        @RequestParam(value = "personId", required = false) String personId,
                        @RequestParam(value = "currentPage", required = false) Integer currentPage,
                        @RequestParam(value = "count", required = false) Integer count,
                        HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        try {
            User user = (User) request.getSession().getAttribute("loginUser");

            if (year == null) {
                year = Quota.getCurrentYear();
            }

            if (currentPage == null) {
                currentPage = 0;
            }

            if (count == null || count == 0) {
                count = 10;
            }

            if ("NoOpen".equals(path)) {
                int maxCount = infoService.getCountByNoOpen(year, user.getDepartmentId(), personName, personId);
                int maxPage = (maxCount - 1) / count + 1;

                model.addAttribute("maxPage", maxPage);

                model.addAttribute("list", infoService.findByNoOpen(year, user.getDepartmentId(), currentPage * count, count, personName, personId));
                model.addAttribute("pages", Common.pages(mid, currentPage, maxPage, "", ""));
            } else if ("NoOver".equals(path)) {
                int maxCount = infoService.getCountByNoOver(year, user.getDepartmentId(), personName, personId);
                int maxPage = (maxCount - 1) / count + 1;

                model.addAttribute("maxPage", maxPage);

                model.addAttribute("list", infoService.findByNoOver(year, user.getDepartmentId(), currentPage * count, count, personName, personId));
                model.addAttribute("pages", Common.pages(mid, currentPage, maxPage, "", ""));
            } else if ("NoAcceptance".equals(path)) {
                int maxCount = infoService.getCountByNoAcceptance(year, user.getDepartmentId(), personName, personId);
                int maxPage = (maxCount - 1) / count + 1;

                model.addAttribute("maxPage", maxPage);

                model.addAttribute("list", infoService.findByNoAcceptance(year, user.getDepartmentId(), currentPage * count, count, personName, personId));
                model.addAttribute("pages", Common.pages(mid, currentPage, maxPage, "", ""));
            }
            model.addAttribute("count", count);
            model.addAttribute("mid", mid);
            model.addAttribute("user", user);
            model.addAttribute("path", path);
            model.addAttribute("year", year);
            model.addAttribute("personName", personName);
            model.addAttribute("personId", personId);
            model.addAttribute("dicList", dicService.findAll());
        } catch (Exception e) {
            log.error("/bk/info/other" + mid, e);
        }

        return "/bk/info/other";
    }

    /**
     * 验收通过信息，此列表主要提供归档功能 2016-08-21 by p
     *
     * @param mid
     * @param year
     * @param currentPage
     * @param count
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @Permissions(target = "loginUser", url = "/index")
    @RequestMapping("/bk/info/acceptanceInfo/{mid}")
    public String acceptanceInfo(@PathVariable("mid") Integer mid,
                                 @RequestParam(value = "year", required = false) Integer year,
                                 @RequestParam(value = "personName", required = false) String personName,
                                 @RequestParam(value = "personId", required = false) String personId,
                                 @RequestParam(value = "currentPage", required = false) Integer currentPage,
                                 @RequestParam(value = "count", required = false) Integer count,
                                 HttpServletRequest request, Model model) throws Exception {
        try {
            User user = (User) request.getSession().getAttribute("loginUser");

            if (year == null) {
                year = Quota.getCurrentYear();
            }

            if (currentPage == null) {
                currentPage = 0;
            }

            if (count == null || count == 0) {
                count = 10;
            }

            int maxCount = infoService.getCountByAcceptanceInfo(year, user, personName, personId);
            int maxPage = (maxCount - 1) / count + 1;

            model.addAttribute("count", count);
            model.addAttribute("maxPage", maxPage);
            model.addAttribute("mid", mid);
            model.addAttribute("user", user);

            model.addAttribute("list", infoService.findByAcceptanceInfo(year, user, currentPage * count, count, personName, personId));
            model.addAttribute("pages", Common.pages(mid, currentPage, maxPage, "", ""));
            model.addAttribute("year", year);
            model.addAttribute("personName", personName);
            model.addAttribute("personId", personId);
            model.addAttribute("dicList", dicService.findAll());
        } catch (Exception e) {
            log.error("/bk/info/acceptanceInfo/" + mid, e);
        }

        return "/bk/info/acceptanceInfo";
    }


    /**
     * 审核信息
     *
     * @param mid
     * @param year
     * @param currentPage
     * @param count
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @Permissions(target = "loginUser", url = "/index")
    @RequestMapping("/bk/info/auditInfo/{mid}")
    public String auditInfo(@PathVariable("mid") Integer mid,
                            @RequestParam(value = "year", required = false) Integer year,
                            @RequestParam(value = "personName", required = false) String personName,
                            @RequestParam(value = "personId", required = false) String personId,
                            @RequestParam(value = "currentPage", required = false) Integer currentPage,
                            @RequestParam(value = "count", required = false) Integer count,
                            HttpServletRequest request, Model model) throws Exception {
        try {
            User user = (User) request.getSession().getAttribute("loginUser");

            if (year == null) {
                year = Quota.getCurrentYear();
            }

            if (currentPage == null) {
                currentPage = 0;
            }

            if (count == null || count == 0) {
                count = 10;
            }

            int maxCount = infoService.getCountByAuditInfo(year, user, personName, personId);
            int maxPage = (maxCount - 1) / count + 1;

            model.addAttribute("count", count);
            model.addAttribute("maxPage", maxPage);
            model.addAttribute("mid", mid);
            model.addAttribute("user", user);

            model.addAttribute("list", infoService.findByAuditInfo(year, user, currentPage * count, count, personName, personId));
            model.addAttribute("pages", Common.pages(mid, currentPage, maxPage, "", ""));
            model.addAttribute("year", year);
            model.addAttribute("personName", personName);
            model.addAttribute("personId", personId);
            model.addAttribute("dicList", dicService.findAll());
        } catch (Exception e) {
            log.error("/bk/info/auditInfo/" + mid, e);
        }

        return "/bk/info/auditInfo";
    }

    /**
     * 退回信息
     *
     * @param mid
     * @param year
     * @param currentPage
     * @param count
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @Permissions(target = "loginUser", url = "/index")
    @RequestMapping("/bk/info/backInfo/{mid}")
    public String backInfo(@PathVariable("mid") Integer mid,
                           @RequestParam(value = "year", required = false) Integer year,
                           @RequestParam(value = "personName", required = false) String personName,
                           @RequestParam(value = "personId", required = false) String personId,
                           @RequestParam(value = "currentPage", required = false) Integer currentPage,
                           @RequestParam(value = "count", required = false) Integer count,
                           HttpServletRequest request, Model model) throws Exception {
        try {
            User user = (User) request.getSession().getAttribute("loginUser");

            if (year == null) {
                year = Quota.getCurrentYear();
            }

            if (currentPage == null) {
                currentPage = 0;
            }

            if (count == null || count == 0) {
                count = 10;
            }

            int maxCount = infoService.getCountByBackInfo(year, user, personName, personId);
            int maxPage = (maxCount - 1) / count + 1;

            model.addAttribute("count", count);
            model.addAttribute("maxPage", maxPage);
            model.addAttribute("mid", mid);
            model.addAttribute("user", user);

            model.addAttribute("list", infoService.findByBackInfo(year, user, currentPage * count, count, personName, personId));
            model.addAttribute("pages", Common.pages(mid, currentPage, maxPage, "", ""));
            model.addAttribute("year", year);
            model.addAttribute("personName", personName);
            model.addAttribute("personId", personId);
            model.addAttribute("dicList", dicService.findAll());
        } catch (Exception e) {
            log.error("/bk/info/backInfo/" + mid, e);
        }

        return "/bk/info/backInfo";
    }

    /**
     * 等待提交信息
     *
     * @param mid
     * @param year
     * @param currentPage
     * @param count
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @Permissions(target = "loginUser", url = "/index")
    @RequestMapping("/bk/info/waitSubmit/{mid}")
    public String waitSubmit(@PathVariable("mid") Integer mid,
                             @RequestParam(value = "personName", required = false) String personName,
                             @RequestParam(value = "personId", required = false) String personId,
                             @RequestParam(value = "year", required = false) Integer year,
                             @RequestParam(value = "currentPage", required = false) Integer currentPage,
                             @RequestParam(value = "count", required = false) Integer count,
                             HttpServletRequest request, Model model) throws Exception {
        try {
            User user = (User) request.getSession().getAttribute("loginUser");

            if (year == null) {
                year = Quota.getCurrentYear();
            }

            if (currentPage == null) {
                currentPage = 0;
            }

            if (count == null || count == 0) {
                count = 10;
            }

            int maxCount = infoService.getCountByEdit(year, user, personName, personId);
            int maxPage = (maxCount - 1) / count + 1;
            model.addAttribute("count", count);
            model.addAttribute("maxPage", maxPage);
            model.addAttribute("mid", mid);
            model.addAttribute("user", user);
            model.addAttribute("list", infoService.findByEdit(year, user, currentPage * count, count, personName, personId));
            model.addAttribute("pages", Common.pages(mid, currentPage, maxPage, "", ""));
            model.addAttribute("year", year);
            model.addAttribute("personName", personName);
            model.addAttribute("personId", personId);
            model.addAttribute("dicList", dicService.findAll());
        } catch (Exception e) {
            log.error("/bk/info/waitSubmit/" + mid, e);
        }

        return "/bk/info/waitSubmit";
    }

    /**
     * 等待审核信息
     *
     * @param mid
     * @param year
     * @param currentPage
     * @param count
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @Permissions(target = "loginUser", url = "/index")
    @RequestMapping("/bk/info/waitAudit/{mid}")
    public String waitAudit(@PathVariable("mid") Integer mid,
                            @RequestParam(value = "year", required = false) Integer year,
                            @RequestParam(value = "personName", required = false) String personName,
                            @RequestParam(value = "personId", required = false) String personId,
                            @RequestParam(value = "departmentId", required = false) String departmentId,
                            @RequestParam(value = "currentPage", required = false) Integer currentPage,
                            @RequestParam(value = "count", required = false) Integer count,
                            HttpServletRequest request, Model model) throws Exception {
        try {
            User user = (User) request.getSession().getAttribute("loginUser");

            if (year == null) {
                year = Quota.getCurrentYear();
            }

            if (currentPage == null) {
                currentPage = 0;
            }

            if (count == null || count == 0) {
                count = 10;
            }

            int maxCount = infoService.getCountByWaitAudit(year, user, personName, personId, departmentId);
            int maxPage = (maxCount - 1) / count + 1;
            model.addAttribute("count", count);
            model.addAttribute("maxPage", maxPage);
            model.addAttribute("mid", mid);
            model.addAttribute("user", user);
            model.addAttribute("list", infoService.findByWaitAudit(year, user, currentPage * count, count, personName, personId, departmentId));
            model.addAttribute("pages", Common.pages(mid, currentPage, maxPage, "", ""));
            model.addAttribute("year", year);
            model.addAttribute("personName", personName);
            model.addAttribute("personId", personId);
            model.addAttribute("departmentId", departmentId);
            model.addAttribute("dicList", dicService.findAll());
        } catch (Exception e) {
            log.error("/bk/info/waitAudit/" + mid, e);
        }

        return "/bk/info/waitAudit";
    }

    @Permissions(target = "loginUser", url = "")
    @RequestMapping(value = "/bk/info/archive/{mid}", method = RequestMethod.POST)
    public void archive(@PathVariable("mid") Integer mid, @RequestParam("ids") String ids,
                        Model model,
                        HttpServletRequest request, HttpServletResponse response) throws Exception {
        int msg = 1;
        try {
            User user = (User) request.getSession().getAttribute("loginUser");

            infoService.archive(ids, user);
        } catch (Exception e) {
            if (e.getMessage() != null) {
                if (e.getMessage().equals("-1")) {
                    msg = -1;
                    log.warn("/bk/info/archive?id=" + ids, "归档信息错误！！！");
                } else {
                    msg = 0;
                    log.error("/bk/info/archive?id=" + ids, e);
                }
            } else {
                msg = 0;
                log.error("/bk/info/archive?id=" + ids, e);
            }
        }

        Common.print(response, msg);
    }

    /**
     * 提交审核
     *
     * @param mid
     * @param id
     * @param content
     * @param model
     * @param request
     * @param response
     * @throws Exception
     */
    @Permissions(target = "loginUser", url = "")
    @RequestMapping(value = "/bk/info/submit/{mid}", method = RequestMethod.POST)
    public void submit(@PathVariable("mid") Integer mid, @RequestParam("id") Integer id,
                       @RequestParam("auditInfo") String content, Model model,
                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        int msg = 1;
        try {
            User user = (User) request.getSession().getAttribute("loginUser");

            infoService.submit(user, id, content);
        } catch (Exception e) {
            msg = 0;
            log.error("/bk/info/del?id=" + id, e);

            if (e.getMessage() != null) {
                if (e.getMessage().equals("-1")) {
                    msg = -1;
                    log.warn("/bk/info/submit?id=" + id, "上报信息错误！！！");
                } else if (e.getMessage().equals("-2")) {
                    msg = -1;
                    log.warn("/bk/info/submit?id=" + id, "上报信息状态错误！！！");
                } else if (e.getMessage().equals("-3")) {
                    msg = -1;
                    log.warn("/bk/info/submit?id=" + id, "上级机构不存在！！！");
                } else if (e.getMessage().equals("-4")) {
                    msg = -1;
                    log.warn("/bk/info/submit?id=" + id, "没有权限进行审核操作！！！");
                } else {
                    msg = 0;
                    log.error("/bk/info/submit?id=" + id, e);
                }
            } else {
                msg = 0;
                log.error("/bk/info/submit?id=" + id, e);
            }
        }

        Common.print(response, msg);
    }

    /**
     * 批量提交审核
     *
     * @param mid
     * @param ids
     * @param content
     * @param model
     * @param request
     * @param response
     * @throws Exception
     */
    @Permissions(target = "loginUser", url = "")
    @RequestMapping(value = "/bk/info/batchSubmit/{mid}", method = RequestMethod.POST)
    public void submit(@PathVariable("mid") Integer mid, @RequestParam("ids") String ids,
                       @RequestParam("auditInfo") String content, Model model,
                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        int msg = 1;
        try {
            User user = (User) request.getSession().getAttribute("loginUser");

            infoService.batchSubmit(user, ids, content);
        } catch (Exception e) {
            msg = 0;
            log.error("/bk/info/batchSubmit?id=" + ids, e);

            if (e.getMessage() != null) {
                if (e.getMessage().equals("-1")) {
                    msg = -1;
                    log.warn("/bk/info/batchSubmit?id=" + ids, "上报信息错误！！！");
                } else if (e.getMessage().equals("-2")) {
                    msg = -1;
                    log.warn("/bk/info/batchSubmit?id=" + ids, "上报信息状态错误！！！");
                } else if (e.getMessage().equals("-3")) {
                    msg = -1;
                    log.warn("/bk/info/batchSubmit?id=" + ids, "上级机构不存在！！！");
                } else if (e.getMessage().equals("-4")) {
                    msg = -1;
                    log.warn("/bk/info/batchSubmit?id=" + ids, "没有权限进行审核操作！！！");
                } else {
                    msg = 0;
                    log.error("/bk/info/batchSubmit?id=" + ids, e);
                }
            } else {
                msg = 0;
                log.error("/bk/info/batchSubmit?id=" + ids, e);
            }
        }

        Common.print(response, msg);
    }

    /**
     * 审核退回
     *
     * @param mid
     * @param id
     * @param content
     * @param model
     * @param request
     * @param response
     * @throws Exception
     */
    @Permissions(target = "loginUser", url = "")
    @RequestMapping(value = "/bk/info/back/{mid}", method = RequestMethod.POST)
    public void back(@PathVariable("mid") Integer mid, @RequestParam("id") Integer id,
                     @RequestParam("auditInfo") String content, Model model,
                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        int msg = 1;
        try {
            User user = (User) request.getSession().getAttribute("loginUser");

            infoService.back(user, id, content);
        } catch (Exception e) {
            msg = 0;
            log.error("/bk/info/back?id=" + id, e);

            if (e.getMessage() != null) {
                if (e.getMessage().equals("-1")) {
                    msg = -1;
                    log.warn("/bk/info/back?id=" + id, "退回信息错误！！！");
                } else if (e.getMessage().equals("-2")) {
                    msg = -1;
                    log.warn("/bk/info/back?id=" + id, "退回信息状态错误！！！");
                } else if (e.getMessage().equals("-4")) {
                    msg = -1;
                    log.warn("/bk/info/back?id=" + id, "没有权限进行审核操作！！！");
                } else {
                    msg = 0;
                    log.error("/bk/info/back?id=" + id, e);
                }
            } else {
                msg = 0;
                log.error("/bk/info/del?back=" + id, e);
            }
        }

        Common.print(response, msg);
    }

    /**
     * 批量审核退回
     *
     * @param mid
     * @param ids
     * @param content
     * @param model
     * @param request
     * @param response
     * @throws Exception
     */
    @Permissions(target = "loginUser", url = "")
    @RequestMapping(value = "/bk/info/batchBack/{mid}", method = RequestMethod.POST)
    public void batchBack(@PathVariable("mid") Integer mid, @RequestParam("ids") String ids,
                          @RequestParam("auditInfo") String content, Model model,
                          HttpServletRequest request, HttpServletResponse response) throws Exception {
        int msg = 1;
        try {
            User user = (User) request.getSession().getAttribute("loginUser");

            infoService.batchBack(user, ids, content);
        } catch (Exception e) {
            msg = 0;
            log.error("/bk/info/batchBack?id=" + ids, e);

            if (e.getMessage() != null) {
                if (e.getMessage().equals("-1")) {
                    msg = -1;
                    log.warn("/bk/info/batchBack?id=" + ids, "退回信息错误！！！");
                } else if (e.getMessage().equals("-2")) {
                    msg = -1;
                    log.warn("/bk/info/batchBack?id=" + ids, "退回信息状态错误！！！");
                } else if (e.getMessage().equals("-4")) {
                    msg = -1;
                    log.warn("/bk/info/batchBack?id=" + ids, "没有权限进行审核操作！！！");
                } else {
                    msg = 0;
                    log.error("/bk/info/batchBack?id=" + ids, e);
                }
            } else {
                msg = 0;
                log.error("/bk/info/del?batchBack=" + ids, e);
            }
        }

        Common.print(response, msg);
    }

    /**
     * 修改 2015-08-05 by p
     *
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @Permissions(target = "loginUser", url = "/index")
    @RequestMapping("/bk/info/edit{path}/{mid}")
    public String edit(@PathVariable("mid") Integer mid, @PathVariable("path") String path,
                       @RequestParam(value = "id", required = false) Integer id, Model model,
                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            User user = (User) request.getSession().getAttribute("loginUser");

            Info info = new Info();
            info.setDepartmentId(user.getDepartmentId());
            info.setDepartmentName(user.getDepartmentName());
            info.setSumFund(0d);
            info.setGrantProvinceFund(0d);
            info.setGrantCountiesFund(0d);
            info.setPersonSelfFund(0d);
            info.setDate(new Date());
            info.setState(Info.STATE_EDIT);
            if (id != null) {
                if (id != 0) {
                    info = infoService.findById(id);
                }
            }

            Department department = departmentService.findById(user.getDepartmentId());
            List<Department> sonDepartmentList = departmentService.findByFatherId(department.getId());

            model.addAttribute("mid", mid);
            model.addAttribute("info", info);

            // 查找所有父机构
            String departmentList = "";
            Department fd = departmentService.findById(info.getDepartmentId());
            while (fd != null) {
                fd = departmentService.findFatherDepartment(fd);
                if (fd != null) {
                    departmentList = fd.getName() + departmentList;
                }
            }
            departmentList += info.getDepartmentName();
            model.addAttribute("departmentList", departmentList);

            if (info.getId() != null && info.getId() != 0) {
                model.addAttribute("auditList", auditService.findByInfoId(info.getId()));
            }
            model.addAttribute("user", user);
            model.addAttribute("department", department);
            model.addAttribute("sonDepartmentList", sonDepartmentList);
            model.addAttribute("dicList", dicService.findAll());
        } catch (Exception e) {
            log.error("/bk/info/edit" + path + "?id=" + id, e);
        }

        return "/bk/info/add" + path;
    }

    /**
     * 保存 2015-08-12 by p
     *
     * @param mid
     * @param info
     * @param request
     * @param response
     * @throws Exception
     */
    @Permissions(target = "loginUser", url = "")
    @RequestMapping(value = "/bk/info/save/{mid}", method = RequestMethod.POST)
    public void save(@PathVariable("mid") Integer mid, @ModelAttribute("info") Info info,
                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        int msg = 1;
        try {
            User user = (User) request.getSession().getAttribute("loginUser");

            CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

            commonsMultipartResolver.setDefaultEncoding("utf-8");

            if (commonsMultipartResolver.isMultipart(request)) {
                String path = request.getSession().getServletContext().getRealPath("/");

                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

                MultipartFile file = multiRequest.getFile("personImg");
                if (file != null) {
                    String ext = Common.getExtensionName(file.getOriginalFilename());
                    if (ext == "") {
                        throw new RuntimeException("-5");
                    }
                    String fileName = "/uploads/" + new Date().getTime() + "." + ext;
                    Common.zoomImageScale(file.getInputStream(), path + fileName, 500);
                    info.setPersonImage(fileName);
                }

                file = multiRequest.getFile("acceptanceImg");
                if (file != null) {
                    String ext = Common.getExtensionName(file.getOriginalFilename());
                    if (ext == "") {
                        throw new RuntimeException("-5");
                    }
                    String fileName = "/uploads/" + new Date().getTime() + "." + ext;
                    Common.zoomImageScale(file.getInputStream(), path + fileName, 500);
                    info.setAcceptanceImage(fileName);
                }

                file = multiRequest.getFile("fundSendImg");
                if (file != null) {
                    String ext = Common.getExtensionName(file.getOriginalFilename());
                    if (ext == "") {
                        throw new RuntimeException("-5");
                    }
                    String fileName = "/uploads/" + new Date().getTime() + "." + ext;
                    Common.zoomImageScale(file.getInputStream(), path + fileName, 500);
                    info.setFundSendImage(fileName);
                }

                file = multiRequest.getFile("houseOldImg");
                if (file != null) {
                    String ext = Common.getExtensionName(file.getOriginalFilename());
                    if (ext == "") {
                        throw new RuntimeException("-5");
                    }
                    String fileName = "/uploads/" + new Date().getTime() + "." + ext;
                    Common.zoomImageScale(file.getInputStream(), path + fileName, 500);
                    info.setHouseOldImage(fileName);
                }

                file = multiRequest.getFile("houseBuildingImg");
                if (file != null) {
                    String ext = Common.getExtensionName(file.getOriginalFilename());
                    if (ext == "") {
                        throw new RuntimeException("-5");
                    }
                    String fileName = "/uploads/" + new Date().getTime() + "." + ext;
                    Common.zoomImageScale(file.getInputStream(), path + fileName, 500);
                    info.setHouseBuildingImage(fileName);
                }

                file = multiRequest.getFile("houseOutNewImg");
                if (file != null) {
                    String ext = Common.getExtensionName(file.getOriginalFilename());
                    if (ext == "") {
                        throw new RuntimeException("-5");
                    }
                    String fileName = "/uploads/" + new Date().getTime() + "." + ext;
                    Common.zoomImageScale(file.getInputStream(), path + fileName, 500);
                    info.setHouseOutNewImage(fileName);
                }

                file = multiRequest.getFile("houseInNewImg");
                if (file != null) {
                    String ext = Common.getExtensionName(file.getOriginalFilename());
                    if (ext == "") {
                        throw new RuntimeException("-5");
                    }
                    String fileName = "/uploads/" + new Date().getTime() + "." + ext;
                    Common.zoomImageScale(file.getInputStream(), path + fileName, 500);
                    info.setHouseInNewImage(fileName);
                }

                file = multiRequest.getFile("personDelegateImg");
                if (file != null) {
                    String ext = Common.getExtensionName(file.getOriginalFilename());
                    if (ext == "") {
                        throw new RuntimeException("-5");
                    }
                    String fileName = "/uploads/" + new Date().getTime() + "." + ext;
                    Common.zoomImageScale(file.getInputStream(), path + fileName, 500);
                    info.setPersonDelegateImage(fileName);
                }
            }

            info.setPersonId(info.getPersonId().toUpperCase());
            info.setDepartmentId(user.getDepartmentId());
            info.setDepartmentName(user.getDepartmentName());
            info.setUserId(user.getId());
            info.setUserName(user.getTrueName());
            info.setDate(new Date());
            infoService.save(info);
        } catch (Exception e) {
            msg = 0;
            log.error("/bk/info/save", e);

            if (e.getMessage() != null) {
                if (e.getMessage().equals("-1")) {
                    msg = -1;
                    log.warn("/bk/info/save/", "计划年度" + info.getPlanYear() + "没有剩余的指标进行上报！！！");
                } else if (e.getMessage().equals("-5")) {
                    msg = -10;
                    log.warn("/bk/info/save/", "上传图片错误！！！");
                } else if (e.getMessage().equals("-10")) {
                    msg = -10;
                    log.warn("/bk/info/save/", "身份证号也被注册！！！");
                } else {
                    msg = 0;
                    log.error("/bk/info/save/", e);
                }
            } else {
                msg = 0;
                log.error("/bk/info/save/", e);
            }
        }

        Common.print(response, msg);
    }

    /**
     * 删除 2015-08-05 by p
     *
     * @param id
     * @param model
     * @param response
     * @throws Exception
     */
    @Permissions(target = "loginUser", url = "")
    @RequestMapping(value = "/bk/info/del/{mid}")
    public void del(@PathVariable("mid") Integer mid, @RequestParam("id") Integer id, Model model,
                    HttpServletResponse response) throws Exception {
        int msg = 1;
        try {
            infoService.remove(id);
        } catch (Exception e) {
            msg = 0;
            log.error("/bk/info/del?id=" + id, e);

            if (e.getMessage() != null) {
                if (e.getMessage().equals("-1")) {
                    msg = -1;
                    log.warn("/bk/info/del?id=" + id, "上报信息错误！！！");
                } else if (e.getMessage().equals("-2")) {
                    msg = -1;
                    log.warn("/bk/info/del?id=" + id, "上报信息状态错误！！！");
                } else if (e.getMessage().equals("-3")) {
                    msg = -1;
                    log.warn("/bk/info/del?id=" + id, "指标信息错误！！！");
                } else {
                    msg = 0;
                    log.error("/bk/info/del?id=" + id, e);
                }
            } else {
                msg = 0;
                log.error("/bk/info/del?id=" + id, e);
            }
        }

        Common.print(response, msg);
    }

    /**
     * 检测身份证号
     *
     * @param mid
     * @param id
     * @param idcard
     * @param response
     * @param model
     * @throws Exception
     */
    @Permissions(target = "loginUser", url = "/index")
    @RequestMapping("/bk/info/checkId/{mid}")
    public void checkId(@PathVariable("mid") Integer mid,
                        @RequestParam(value = "id", required = false) Integer id,
                        @RequestParam("idcard") String idcard,
                        HttpServletResponse response, Model model) throws Exception {
        int msg = 1;
        try {
            msg = infoService.checkId(id, idcard);
        } catch (Exception e) {
            msg = -1;
            log.error("/bk/info/checkId?id=" + id, e);
        }

        Common.print(response, msg);
    }

    @Permissions(target = "loginUser", url = "/index")
    @RequestMapping("/bk/info/statisticsByYear/{mid}")
    public String statisticsByYear(@PathVariable("mid") Integer mid,
                                   HttpServletRequest request, Model model) throws Exception {
        try {
            model.addAttribute("dicList", dicService.findAll());
            model.addAttribute("mid", mid);
        } catch (Exception e) {
            log.error("/bk/info/statistics/" + mid, e);
        }

        return "/bk/info/statistics";
    }

    @Permissions(target = "loginUser", url = "/index")
    @RequestMapping("/bk/info/statistics/{mid}")
    public void statistics(@PathVariable("mid") Integer mid,
                           @RequestParam("year") Integer year,
                           HttpServletResponse response, Model model) throws Exception {
        int msg = 1;
        try {
            infoService.statisticsQuota(year);
        } catch (Exception e) {
            msg = -1;
            log.error("/bk/info/statistics?year=" + year, e);
        }

        Common.print(response, msg);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor dateEditor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

}
