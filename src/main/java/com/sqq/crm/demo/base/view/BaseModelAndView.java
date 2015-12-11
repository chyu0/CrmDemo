
package com.sqq.crm.demo.base.view;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.sqq.crm.demo.util.PackageScanUtil;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateModelException;

/**
 * 添加静态类到freemarker中， 方便freemarker可以直接通过类名访问静态方法
 */
public class BaseModelAndView extends ModelAndView {

    private static final Set<String> packages = new LinkedHashSet<String>();

    private static final ModelMap modelMap = new ModelMap();

    static {
        packages.add("com.sqq.crm.demo.enums");
        setStaticModel(packages.toArray(new String[packages.size()]));
    }

    public BaseModelAndView() {
        super();
        this.addAllObjects(modelMap);
    }

    public BaseModelAndView(String viewName) {
        super(viewName);
        this.addAllObjects(modelMap);
    }

    public BaseModelAndView(View view) {
        super(view);
        this.addAllObjects(modelMap);
    }

    public BaseModelAndView(String viewName, Map<String, ?> model) {
        super(viewName, model);
        this.addAllObjects(modelMap);
    }

    public BaseModelAndView(View view, Map<String, ?> model) {
        super(view, model);
        this.addAllObjects(modelMap);
    }

    public BaseModelAndView(String viewName, String modelName, Object modelObject) {
        super(viewName, modelName, modelObject);
        this.addAllObjects(modelMap);
    }

    public BaseModelAndView(View view, String modelName, Object modelObject) {
        super(view, modelName, modelObject);
        this.addAllObjects(modelMap);
    }

    private static void setStaticModel(String[] packname) {

        Class<?>[] defaultStaticClasses = PackageScanUtil.scanBasePackage(packname);
        for (Class<?> clz : defaultStaticClasses) {
            String name = clz.getSimpleName();
            modelMap.addAttribute(name, getStaticModel(clz));
        }
    }

    private static Object getStaticModel(Class<?> clz) {

        BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
        try {
            return wrapper.getStaticModels().get(clz.getName());
        } catch (TemplateModelException e) {
            e.printStackTrace();
        }
        return null;
    }
}
