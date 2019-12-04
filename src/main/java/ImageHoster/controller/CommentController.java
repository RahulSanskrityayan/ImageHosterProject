package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
//import ImageHoster.service.CommentService;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class CommentController {

//    @Autowired
    //private CommentService commentService;

    @Autowired
    private ImageService imageservice;

    @Autowired
    private CommentService commentService;

    //Added Logic for Adding Comment feature on the Image
    //Used RequestParam to fetch the comment posted by the user on the image
    //Called Method defined setComment in ImageService class to persist the comment in Database
    //Added Logic to return the image to the user showing his comment on the Picture
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String addComment(@PathVariable("imageId") Integer imageId, @RequestParam("comment") String comment, HttpSession session, Model model) {

        Image image = imageservice.getImageById(imageId);
        User user = (User) session.getAttribute("loggeduser");
        Comment userComment = new Comment();
        userComment.setText(comment);
        userComment.setUser(user);
        userComment.setImage(image);
        userComment.setDate(new Date());
        commentService.setComment(userComment);
        Image imageWithComments = imageservice.getImageById(imageId);
        model.addAttribute("comments", userComment);
        model.addAttribute("image", imageWithComments);
        model.addAttribute("tags", imageWithComments.getTags());

        return "images/image";


    }
}
