package services.project;

import models.Project;
import repositories.DataSourceSingleton;
import repositories.project.ProjectReposiory;
import repositories.user.UsersRepository;
import services.user.UserService;

public class ProjectService {
    private ProjectReposiory projectReposiory;
    private UsersRepository usersRepository;
    private UserService userService;

    public ProjectService(ProjectReposiory projectReposiory) {
        this.projectReposiory = projectReposiory;
        usersRepository = new UsersRepository(DataSourceSingleton.getDataSource());
        userService = new UserService(usersRepository);
    }

    public void addProject(Project project) {
        usersRepository.changeRoleToAdmin(project.getMainAdmin().getId());
        projectReposiory.save(project);
    }


}
