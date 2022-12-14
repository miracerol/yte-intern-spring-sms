package yte.intern.sms.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import yte.intern.sms.common.response.MessageResponse;
import yte.intern.sms.common.response.ResponseType;
import yte.intern.sms.homework.entity.Homework;
import yte.intern.sms.homework.repository.HomeworkRepository;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeworkService {
    String path = "src/main/resources/static/homeworks/";
    private final HomeworkRepository homeworkRepository;

    public MessageResponse addHomework(Homework homework) {
        homeworkRepository.save(homework);
        return new MessageResponse(ResponseType.SUCCESS, "Homework has been added successfully");
    }

    public MessageResponse deleteHomeworkById(Long id) {
        homeworkRepository.deleteById(id);
        return new MessageResponse(ResponseType.SUCCESS, "Homework has been deleted successfully");
    }

    public MessageResponse updateHomework(Homework homework) {
        homeworkRepository.save(homework);
        return new MessageResponse(ResponseType.SUCCESS, "Homework has been updated successfully");
    }

    public List<String> saveFiles(MultipartFile[] files) {
        createDirIfNotExist();
        List<String> fileNames = new ArrayList<>();
        Arrays.asList(files).stream().forEach(file -> {
            byte[] bytes = new byte[0];
            try {
                bytes = file.getBytes();
                Files.write(new File(path + "/" + file.getOriginalFilename()).toPath(), bytes);
                fileNames.add(file.getOriginalFilename());

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return fileNames;
    }

    public List<byte[]> getFiles(List<String> fileNames) {
        List<byte[]> files = new ArrayList<>();
        fileNames.stream().forEach(fileName -> {
            try {
                files.add(Files.readAllBytes(new File(path + "/" + fileName).toPath()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return files;
    }
    private void createDirIfNotExist() {
        //create directory to save the files
        File directory = new File(path);
        if (! directory.exists()){
            directory.mkdir();
        }
    }


    public List<Homework> getHomeworksByLessonId(long lessonId) {
        return homeworkRepository.findAllByLessonId(lessonId);
    }

    public List<Homework> getAllHomeworks() {
        return homeworkRepository.findAll();
    }
    public List<Homework> getHomeworksByStudentId(long studentId) {
        List<Homework> homeworkList = homeworkRepository.findAllByStudentId(studentId);
        return homeworkList;
    }

    public Homework getHomeworkByLessonIdAndStudentId(long lessonId, long studentId) {
        return homeworkRepository.findByLessonIdAndStudentId(lessonId, studentId);
    }

    public Homework getHomeworkByStudentIdAndHomeworkPostId(long studentId, long homeworkPostId) {
        return homeworkRepository.findByStudentIdAndHomeworkPostId(studentId, homeworkPostId);
    }

    public MessageResponse gradeHomework(long homeworkId, int grade) {
        Homework homework = homeworkRepository.findById(homeworkId).get();
        homework.setGrade(grade);
        homeworkRepository.save(homework);
        return new MessageResponse(ResponseType.SUCCESS, "Homework has been graded successfully");
    }
}
