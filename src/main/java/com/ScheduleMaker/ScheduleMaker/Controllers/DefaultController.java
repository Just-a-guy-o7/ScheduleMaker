package com.ScheduleMaker.ScheduleMaker.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ScheduleMaker.ScheduleMaker.DataTransferObjects.TopicDTO;
import com.ScheduleMaker.ScheduleMaker.Helpers.AiResponseParserHelper;
import com.ScheduleMaker.ScheduleMaker.Services.GeminiService;

import reactor.core.publisher.Mono;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/schedule")
public class DefaultController {

    private final GeminiService geminiService;

    public DefaultController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }




    @GetMapping("")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> testMethod() {
        return new ResponseEntity<>("All good",HttpStatus.OK);
    }
    @PostMapping("/topics")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<ArrayList<TopicDTO>> getTopicsFromSubName(@RequestParam String subName) {
        String syllabusText="";
        String prompt = "You are an academic syllabus and skill-curriculum analyzer. You will be given a \r\n" + //
                        "subject or hobby name, and OPTIONALLY raw syllabus text. Break the subject down \r\n" + //
                        "into a structured, ordered list of study/practice topics.\r\n" + //
                        "\r\n" + //
                        "Two modes:\r\n" + //
                        "A) SYLLABUS PROVIDED: If raw syllabus text is given and is non-empty and legible, \r\n" + //
                        "   extract topics from that text following the rules below.\r\n" + //
                        "B) NO SYLLABUS PROVIDED: If no syllabus text is given (or it is empty/garbled), \r\n" + //
                        "   and the subject/hobby name is a real, recognizable subject or hobby, generate \r\n" + //
                        "   a standard, well-established curriculum for it from your own knowledge — as if \r\n" + //
                        "   synthesizing a typical intro-to-advanced course or practice progression for \r\n" + //
                        "   that subject/hobby (e.g. for guitar: open chords, strumming patterns, barre \r\n" + //
                        "   chords, basic music theory, fingerpicking, etc.; for crocheting: holding hook \r\n" + //
                        "   and yarn, chain stitch, single crochet, tension control, reading patterns, \r\n" + //
                        "   increases/decreases, etc.). Only do this if the subject/hobby name is coherent; \r\n" + //
                        "   if it is nonsensical or empty, return an empty topics array.\r\n" + //
                        "\r\n" + //
                        "Rules:\r\n" + //
                        "1. Extract or generate topics at a granular but studyable/practicable level — not \r\n" + //
                        "   whole chapters or broad skill areas, not single sentences or micro-steps. A \r\n" + //
                        "   good topic is something a learner could realistically focus on in one sitting \r\n" + //
                        "   (e.g. \"Newman Projections\" not \"Organic Chemistry Unit 3\"; \"Basic Chain \r\n" + //
                        "   Stitch\" not \"Crochet Fundamentals\").\r\n" + //
                        "2. Order topics the way they'd naturally be learned: preserve the order they \r\n" + //
                        "   appear in the syllabus if one is provided (Mode A); otherwise use a sensible \r\n" + //
                        "   beginner-to-advanced learning progression (Mode B). This becomes order_index.\r\n" + //
                        "3. For min_hours, estimate the minimum focused study/practice hours a learner with \r\n" + //
                        "   NO prior exposure would need to reach basic competency in that topic alone \r\n" + //
                        "   (not mastery, not performance-readiness — just \"understands/can do it\"). Base \r\n" + //
                        "   this on topic complexity and depth (implied by syllabus wording in Mode A, or \r\n" + //
                        "   by typical learning-curve knowledge in Mode B), not on any assumed total time \r\n" + //
                        "   budget — estimate each topic independently.\r\n" + //
                        "4. For base_priority, rate 1-5 how central this topic is to the subject/hobby as a \r\n" + //
                        "   whole — in Mode A, based on how much emphasis/depth the syllabus gives it (more \r\n" + //
                        "   subtopics or repeated mention = higher priority); in Mode B, based on how \r\n" + //
                        "   foundational/essential the topic is to competently practicing the hobby overall. \r\n" + //
                        "   This is NOT about difficulty.\r\n" + //
                        "5. Do not invent topics inconsistent with the subject/hobby. Do not skip topics \r\n" + //
                        "   even if they seem minor — the caller will handle filtering.\r\n" + //
                        "6. Output ONLY valid JSON. No preamble, no explanation, no markdown code fences, \r\n" + //
                        "   no trailing commentary. If the subject/hobby is empty, nonsensical, or \r\n" + //
                        "   unrecognizable, return an empty topics array.\r\n" + //
                        "\r\n" + //
                        "Output schema (exactly this structure, nothing else):\r\n" + //
                        "{\r\n" + //
                        "  \"topics\": [\r\n" + //
                        "    {\r\n" + //
                        "      \"order_index\": <integer, starting at 1>,\r\n" + //
                        "      \"name\": \"<string, topic title>\",\r\n" + //
                        "      \"min_hours\": <number, e.g. 2.5>,\r\n" + //
                        "      \"base_priority\": <integer 1-5>\r\n" + //
                        "    }\r\n" + //
                        "  ]\r\n" + //
                        "}" +
                        "Subject/Hobby: " + subName + "\r\n" + //
                        "Syllabus text (may be empty): " + syllabusText + "\r\n" + //
                        "Return the topics JSON as specified.";
        System.out.println("here");
        Mono<String> response = ask(prompt);
        String aiResponse = response.block();
        System.out.println(aiResponse);
        if (aiResponse != null) {
            ArrayList<TopicDTO> topics = AiResponseParserHelper.parseTopics(aiResponse);
            return new ResponseEntity<>(topics, HttpStatus.OK);
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }
    private Mono<String> ask(@RequestParam String question) {
        return geminiService.generate(question);  
    }

}
