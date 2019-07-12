package com.alex.erp.activiti.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alex.erp.basic.vo.Result;
import com.alex.erp.basic.vo.factory.ResultFactory;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

/** 
 *
 * @Description
 * @return  
 * @Throw 
 * @Author Alex ZY Liang
 * @Date 2019/7/10 10:32 AM
 * @Version 0.0.1
 **/
@RestController
@RequestMapping(value = "/activiti")
@Slf4j
public class ActivitiController {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ObjectMapper objectMapper;


    @RequestMapping("/modelList")
    public Result modelList(org.springframework.ui.Model model,HttpServletRequest request){
        log.info("-------------列表页-------------");
        List<Model> models = repositoryService.createModelQuery().orderByCreateTime().desc().list();
        model.addAttribute("models",models);
        String info = request.getParameter("info");
        if(StringUtils.isNotEmpty(info)){
            model.addAttribute("info",info);
        }
        return ResultFactory.buildSuccessResult(models);
//        return "model/list";
    }


    @RequestMapping("/create")
    public void create(HttpServletRequest request, HttpServletResponse response) {
        try {
            ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//            RepositoryService repositoryService = processEngine.getRepositoryService();
//            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.put("stencilset", stencilSetNode);
            Model modelData = repositoryService.newModel();
            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, "model_name");
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
            String description = "Description";
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
            modelData.setMetaInfo(modelObjectNode.toString());
            modelData.setName("Name");
            modelData.setKey("Key");
            //保存模型
            repositoryService.saveModel(modelData);
            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
            response.sendRedirect(request.getContextPath() + "/static/modeler.html?modelId=" + modelData.getId());
        } catch (Exception e) {
            log.warn("创建模型失败：%s",e.getLocalizedMessage());
        }
    }


    @RequestMapping("/delete/{id}")
    public @ResponseBody
    Result deleteModel(@PathVariable("id")String id){
        repositoryService.deleteModel(id);
        return ResultFactory.buildSuccessResult("流程%s已经删除",id);
    }


    @RequestMapping("/deployment/{id}")
    public @ResponseBody Result deploy(@PathVariable("id")String id) throws Exception {

        //获取模型
        Model modelData = repositoryService.getModel(id);
        byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());

        if (bytes == null) {
            return ResultFactory.buildFailResult("模型数据为空，请先设计流程并成功保存，再进行发布。");
//            return "模型数据为空，请先设计流程并成功保存，再进行发布。";
        }

        JsonNode modelNode = new ObjectMapper().readTree(bytes);

        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
        if(model.getProcesses().size()==0){
            return ResultFactory.buildFailResult("数据模型不符要求，请至少设计一条主线流程。");
//            return "数据模型不符要求，请至少设计一条主线流程。";
        }
        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);

        //发布流程
        String processName = modelData.getName() + ".bpmn20.xml";
        Deployment deployment = repositoryService.createDeployment()
                .name(modelData.getName())
                .addString(processName, new String(bpmnBytes, "UTF-8"))
                .deploy();
        modelData.setDeploymentId(deployment.getId());
        repositoryService.saveModel(modelData);
        return ResultFactory.buildSuccessResult("流程发布成功");

    }

    @RequestMapping("/start/{id}")
    public @ResponseBody
    Result startProcess(@PathVariable("id") String id){
        try {


        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("流程启动失败");
        }
        return ResultFactory.buildSuccessResult("流程启动成功");
    }
}
