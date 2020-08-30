package page;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//登录的验证码

@WebServlet("/checkcode")
public class checkcode extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     response.setCharacterEncoding("utf-8");
        int width = 100;
        int height = 30;

        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();


        g.setColor(Color.gray);

        g.fillRect(0, 0, width, height);

        g.setColor(Color.black);

        g.setFont(new Font("����",Font.BOLD,19));
        g.drawRect(0, 0, width-1, height-1);

        String str = "ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz0123456789";

        Random ran = new Random();

        StringBuilder sb = new StringBuilder();
        for(int i =1; i<= 4; i++) {

            int index = ran.nextInt(str.length());
            char ch = str.charAt(index);

            sb.append(ch);

            g.drawString(ch+"", width/5*i,20);

        }
        String checkCodeSession01 = sb.toString();

        request.getSession().setAttribute("checkCodeSession02",checkCodeSession01);
        System.out.println("打印一下checkCodecopyucp类生成的验证码"+checkCodeSession01);

        g.setColor(Color.pink);

        for(int i = 1; i <= 6; i++) {
            int x1 = ran.nextInt(width);
            int x2 = ran.nextInt(width);
            int y1 = ran.nextInt(height);
            int y2 = ran.nextInt(height);

            g.drawLine(x1, x2, y1,y2);
        }

        //共享图片
        ImageIO.write(image, "jpg", response.getOutputStream());


    }
}
