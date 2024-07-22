import { $ } from 'zx';
import _ from 'lodash';
import { select, Separator } from '@inquirer/prompts';

// $.verbose = false;

const workDir = process.cwd();

async function mainEntry() {
  const pwd2 = _.trim((await $`pwd`).stdout);
  if (workDir !== pwd2) {
    console.log(`process.cwd is not equal to shell pwd, ("${workDir}", "${pwd2}")`);
    return;
  }
  console.log(`start from directory ${workDir}`);

  const answer = await select({
    message: 'Select a feature',
    choices: [
      {
        name: 'eum-httpd',
        value: demoEumHttpd,
        description: 'start example httpd server to demonstrate EUM AutoInstrumentation'
      },
      new Separator(),
      {
        name: 'cleanup',
        value: cleanupResources,
        description: 'stop all test resources'
      },
      new Separator(),
      {
        name: 'version',
        value: showVersion,
        description: 'show version information'
      }
    ]
  });

  if (answer) {
    return answer();
  } else {
    console.warn('invalid operation');
  }
}

console.log('👋 EUM Examples~~~~~~~~~~~');
mainEntry().finally(() => {
  console.log('🍷 Bye~');
});

async function showVersion() {
  await $`cat package.json | grep name`;
  const branch = await $`git branch --show-current`;

  console.log(branch);
  console.log(process.cwd());
  console.log(await $`pwd`);

  await Promise.all([$`sleep 3; echo 3`, $`sleep 2; echo 2`, $`sleep 1; echo 1`]);
}

async function parepareDockerNetwork() {
  let answer = await $`docker network ls 2>&1 | grep demnet`.nothrow();
  if (!answer.stdout) {
    console.log(`docker network create demnet`);
    await $`docker network create demnet`;
  }
}

async function stopExistingDocker(name) {
  let answer = await $`docker ps 2>&1 | grep ${name}`.nothrow();
  if (answer.stdout) {
    console.log(`docker stop ${name}`);
    await $`docker stop ${name}`;
  }
}

async function stopAllDockers() {
  await stopExistingDocker('eum-test-httpd24');
  await stopExistingDocker('eum-test-ra-server');
}

async function cleanupResources() {
  await stopAllDockers();
}

async function buildMyReactAdmin() {
  const buildCmd = 'cd my-react-admin && yarn && yarn build';
  return $`docker run -it --rm --name eum-build -v ${workDir}:/work -w /work node:16 bash -c ${buildCmd}`;
}

async function runMyReactAdminInHttpd() {
  await stopExistingDocker('eum-test-httpd24');

  const apacheCmd =
    'cp -f /mypache2.conf /etc/apache2/apache2.conf && a2enmod headers && a2enmod proxy && a2enmod proxy_http && a2enmod rewrite && apache2ctl configtest && apache2-foreground';
  const apacheWww = `${workDir}/my-react-admin/build`;
  const apacheConf = `${workDir}/apache2.conf`;
  return $`docker run -d --rm -v ${apacheWww}:/var/www/html -v ${apacheConf}:/mypache2.conf -p 3390:80 -p 3391:81 -p 33892:82 --network demnet --name eum-test-httpd24 php:7.2-apache bash -c ${apacheCmd}`;
}

async function runMyRaServer() {
  await stopExistingDocker('eum-test-ra-server');

  const runCmd = 'cd my-ra-server && yarn && yarn dev2';
  return $`docker run -d --rm --name eum-test-ra-server --network demnet -v ${workDir}:/work -w /work node:16 bash -c ${runCmd}`;
}

async function demoEumHttpd() {
  await parepareDockerNetwork();
  await buildMyReactAdmin();
  await runMyReactAdminInHttpd();
  await runMyRaServer();

  await $`docker ps -a | grep eum`.nothrow();
}
