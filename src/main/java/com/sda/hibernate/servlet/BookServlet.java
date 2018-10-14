package com.sda.hibernate.servlet;

import com.sda.hibernate.entity.Author;
import com.sda.hibernate.entity.Book;
import com.sda.hibernate.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Collections;

@WebServlet("/book")
public class BookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        /*Book book = new Book(req.getParameter("title"),req.getParameter("isbn"));
        book.setAuthors(Collections.singleton(new Author("Maciej","Spiewak")));
        BookService bookService = new BookService();
        bookService.save(book);*/
        String titleParam = req.getParameter("title");
        session.setAttribute("title", titleParam);
        String isbnParam = req.getParameter("ISBN");
        session.setAttribute("ISBN", isbnParam);

        resp.addCookie(new Cookie("title",req.getParameter("title")));
        resp.addCookie(new Cookie("isbn",req.getParameter("isbn")));

        resp.sendRedirect("book"); //przekierowanie na dana strone

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       /* BookService bookService = new BookService();
        bookService.findAll();*/

        HttpSession session = req.getSession();
        req.setAttribute("title", session.getAttribute("title"));
        req.setAttribute("isbn", session.getAttribute("isbn"));
        req.getRequestDispatcher("book.jsp").forward(req, resp);

    }
}
