package ci;

import ci.dependencies.Logger;
import ci.dependencies.Project;

public record PipelineContext(Project project, Logger log) {
    public boolean testsFailed() {
        if (this.project.hasTests() && this.project.runTests().failed()) {
            log.error("Tests failed");
            return true;
        }

        log.info(this.project.hasTests() ? "Tests passed" : "No tests");
        return false;
    }

    public boolean deployFailed() {
        if (this.project.deploy().failed()) {
            log.error("Deployment failed");
            return true;
        } else {
            log.info("Deployment successful");
            return false;
        }

    }
}
