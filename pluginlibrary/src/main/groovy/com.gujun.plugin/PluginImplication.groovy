package com.gujun.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

public class PluginImplication implements Plugin<Project> {

    void apply(Project project) {

        //每个在build.gradle文件中添加插件（apply plugin: 'com.gujun.plugintest'）的都会执行此方法
        //1、先获取当前运行的module
        //2、在判断当前执行的module是否是运行的module
        //3、是：就添加isApp的代码，添加需要的依赖，和文件资源
        //4、否：设置library

        //build apks时格式：[:app:assembleDebug, :business:home:assembleDebug, :business:mine:assembleDebug]
        //单个运行的格式：[:app:assembleDebug]
        //Studio打开项目的格式：[]
        //clean project的格式：[clean]
        //rebuild project的格式：[clean, :business:mine:assembleDebug, :business:home:assembleDebug,...]
        List<String> taskNames = project.gradle.startParameter.taskNames
        String curBuildModule = project.project.path
        System.out.println("-----------" + curBuildModule)
        if (taskNames.size() > 0) {
            //如果任务不为空，并且第一个任务是当前正在编译的module
            String firstTask = taskNames.get(0)
            boolean isAssemble = firstTask.toUpperCase().contains("ASSEMBLE") || firstTask.contains("aR") || firstTask.toUpperCase().contains("RESGUARD")
            if (isAssemble) {
                String firstTaskModule = firstTask.substring(0, firstTask.lastIndexOf(":"))
                if (curBuildModule == firstTaskModule) {
                    //设置app
                    setApp(project, curBuildModule)
                    //添加需要依赖的app类型的module,需要在gradle.properties中配置
                    addCompileComponents(project)
                } else {
                    if (":app" != curBuildModule) {
                        System.out.println("+++++++++" + curBuildModule + ",," + firstTaskModule)
                        //设置成library
                        setLibrary(project)
                    }
                }
            } else {
                System.out.println("______________" + curBuildModule)
                setNotAssemble(project, curBuildModule)
            }

        } else {
            System.out.println("------------" + curBuildModule)
            setNotAssemble(project, curBuildModule)
        }
    }

    /**
     * 不是编译运行的设置
     * @param project
     * @param curBuildModule
     */
    void setNotAssemble(Project project, String curBuildModule) {
        //如果没有任务把除了app和在gradle.properties中设置isApp=true的所有module设置成library
        boolean isApp = Boolean.parseBoolean((project.properties.get("isApp")))
        if (":app" == curBuildModule) {
            isApp = true
        }
        if (isApp) {
            //设置app
            setApp(project, curBuildModule)
        } else {
            //设置成library
            setLibrary(project)
        }
    }

    /**
     * 将当前project设置成app
     * @param project
     */
    void setApp(Project project, String curBuildModule) {
        if (":app" != curBuildModule) {
            System.out.println("______________" + curBuildModule)
            project.apply plugin: 'com.android.application'
            //设置资源相关
//            project.android.sourceSets {
//                main {
//                    //设置独立运行所需资源的加载位置，需提前配置好
//                    manifest.srcFile 'src/main/runalone/AndroidManifest.xml'
//                    java.srcDirs = ['src/main/java', 'src/main/runalone/java']
//                    res.srcDirs = ['src/main/res', 'src/main/runalone/res']
//                }
//            }
        }
    }

    /**
     * 将当前project设置成library
     * @param project
     */
    void setLibrary(Project project) {
        project.apply plugin: 'com.android.library'
    }

    /**
     * 添加需要依赖的app类型的module,需要在gradle.properties中配置,格式：compileComponents=business:mine,business:media
     * @param project
     */
    void addCompileComponents(Project project) {
        String compileComponents = project.properties.get("compileComponents")
        if (compileComponents == null || compileComponents.isEmpty()) return
        String[] compileComponentsArray = compileComponents.split(",")
        if (compileComponentsArray == null || compileComponentsArray.length == 0) {
            System.out.println("there is no compileComponents")
            return
        }
        for (String str : compileComponentsArray) {
            System.out.println("str-------------" + str)
            project.dependencies.add("implementation", project.project(':' + str))
        }
    }

}