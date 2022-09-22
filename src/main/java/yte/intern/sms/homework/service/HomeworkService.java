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

    public List<Byte[]> getFiles(List<String> fileNames) {
        List<Byte[]> files = new ArrayList<>();
        fileNames.stream().forEach(fileName -> {
            try {
                File file = new File(path + "/" + fileName);
                byte[] bytes = Files.readAllBytes(file.toPath());
                Byte[] bytes1 = new Byte[bytes.length];
                int i = 0;
                for (byte b : bytes) {
                    bytes1[i++] = b;
                }
                files.add(bytes1);
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
    public List<Homework> getHomeworksByStudentId(long studentId) {
        return homeworkRepository.findAllByLessonId(studentId);
    }

    public Homework getHomeworkByLessonIdAndStudentId(long lessonId, long studentId) {
        return homeworkRepository.findByLessonIdAndStudentId(lessonId, studentId);
    }

}
