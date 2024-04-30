package ci;

import ci.dependencies.Config;
import ci.dependencies.Emailer;
import ci.dependencies.Logger;
import ci.dependencies.Project;

public class Pipeline {
    private final Config config;
    private final Emailer emailer;
    private final Logger log;

    public Pipeline(Config config, Emailer emailer, Logger log) {
        this.config = config;
        this.emailer = emailer;
        this.log = log;
    }

    public void run(Project project) {
        var pipelineContext = new PipelineContext(project, log);

        if (pipelineContext.testsFailed()) {
            sendEmail("Tests failed");
            return;
        }

        if (pipelineContext.deployFailed()) {
            sendEmail("Deployment failed");
            return;
        }

        sendEmail("Deployment completed successfully");
    }

    private void sendEmail(String email) {
        if (config.sendEmailSummary()) {
            log.info("Sending email");
            emailer.send(email);
        } else {
            log.info("Email disabled");
        }
    }
}
