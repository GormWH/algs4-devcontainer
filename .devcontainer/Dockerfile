# Princeton COS 126 / COS 226 (algs4 + introcs) environment
# Princeton requires JDK 11+. We use the Eclipse Temurin 11 image.
# Pin to the Ubuntu 24.04 LTS (noble) variant: the floating "11-jdk" tag rolls
# forward onto in-development Ubuntu releases whose apt mirrors can be mid-sync
# (causing 404 / "File has unexpected size" errors during apt-get update).
FROM eclipse-temurin:11-jdk-noble

# Tolerate transient apt mirror hiccups during the build
RUN printf 'Acquire::Retries "5";\n' > /etc/apt/apt.conf.d/80-retries

# Install the tools the Princeton autoinstaller expects
RUN apt-get update \
    && apt-get install -y --no-install-recommends \
        curl \
        unzip \
        git \
        ca-certificates \
        # X11 libs so StdDraw / StdAudio windows can work when a display is forwarded
        libxext6 libxrender1 libxtst6 libxi6 libfreetype6 \
    && rm -rf /var/lib/apt/lists/*

# Install Princeton's command-line tools (textbook libraries, SpotBugs, PMD,
# Checkstyle, and the javac-algs4 / java-algs4 / javac-introcs / java-introcs
# wrapper scripts). This follows the current official instructions, which
# install everything from lift-cli.zip into /usr/local/lift with wrapper
# scripts placed in /usr/local/bin.
WORKDIR /usr/local
RUN curl -fsSL -O "https://lift.cs.princeton.edu/java/linux/lift-cli.zip" \
    && unzip -o lift-cli.zip \
    && rm lift-cli.zip

# Make sure the wrapper scripts are on PATH
ENV PATH="/usr/local/bin:${PATH}"

# Non-root user: eclipse-temurin:11-jdk (Ubuntu 24.04) already has ubuntu at UID/GID 1000
RUN groupmod -n worker ubuntu \
    && usermod -l worker -d /home/worker -m ubuntu \
    && mkdir -p /home/worker/workspace \
    && chown -R worker:worker /home/worker

USER worker
WORKDIR /home/worker/workspace