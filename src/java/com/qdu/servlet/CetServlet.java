package com.qdu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 这里是创建一个Servlet，负责根据提交的考号和姓名，查询四六级成绩，生成响应 Servlet：Server+let
 * Servlet是服务器端（运行在服务器端）的组件，负责处理请求，生成动态响应 可以通过实现Servlet接口来创建一个Servlet类
 * 回忆：接口中的方法默认都是抽象方法，也就是都是未实现的方法 回忆：实现一个接口，需要实现所有的方法
 *
 */
//Ctrl+Shift+I 修复导入(import)
public class CetServlet implements Servlet {

    //一个Servlet有它的生命周期，在Servlet的整个生命周期中
    //有三个生命周期方法会被调用，如果在某个阶段需要执行一些任务，可以在对应的生命周期方法中写代码
    //三个生命周期方法：init(), service(), destroy()
    //init()方法用于执行一些初始化工作，也就是准备工作
    //在整个生命周期过程中，init()只调用一次
    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    //service()方法中用于处理请求，而且是可以反复处理请求
    //在整个生命周期过程中，service()方法反复被调用，来处理请求
    //service()方法接受两个参数，分别是：
    //1. 请求对象req，包含请求信息，如请求参数
    //2. 响应对象res，包含响应信息，也就是给到客户端的响应，比如查到的成绩
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        //1. 获取请求参数，包括考号和姓名
        //Ctrl+Shift+下 向下复制
        //ServletRequest接口的getParameter()方法用于根据请求参数的名称获取请求参数的值
        //所有的请求参数都是以字符串提交的，所以返回值使用String类型接受
        //如果提交了数字，也是字符串，需要强制转换类型
        String id = req.getParameter("exam_id");
        String name = req.getParameter("exam_name");

        //2. 处理请求，根据考号和姓名查询成绩
        //实际中，查询四六级是要从数据库查询的，这里为了简化，使用随机数模拟一个成绩
        //Random类提供了一些方法，用于生成随机数
        //Ctrl+Shift+I 修复导入(import)
        Random rand = new Random();
        int score = rand.nextInt(711); //生成一个711以内（不包含711）的随机整数

        //3. 生成响应，将查询结果显示给用户
        res.setContentType("text/html;charset=utf-8");
        //ServletResponse接口的getWriter()方法用于获取输出响应内容的打印器
        PrintWriter out = res.getWriter();
        //可以调用write()或println()等方法输出响应内容
        //默认输出的是html内容，所以可以使用各种html元素
        out.println("<h1>查询结果</h1>");
        out.println("<hr>");
        out.write("<br>学号：" + id);
        out.println("<br>姓名：" + name);
        out.println("<br>成绩：" + score);
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    //一个Servlet实例在被摧毁的时候，可以执行一些如释放资源等任务
    //在整个生命周期过程中，destroy()只调用一次
    @Override
    public void destroy() {

    }
}
