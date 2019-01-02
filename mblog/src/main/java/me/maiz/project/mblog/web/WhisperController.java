package me.maiz.project.mblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WhisperController {
    @RequestMapping("whisper")
    public String toWhisper(){
        return "whisper";
    }
}
