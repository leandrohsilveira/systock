(function(module) {
   'use strict';

    module.exports = function(grunt) {

        grunt.initConfig({
            pkg: grunt.file.readJSON('package.json'),
            concat: {
                options: {
                    // define a string to put between each file in the concatenated output
                    separator: ';'
                },
                dist: {
                    // the files to concatenate
                    src: ['src/main/web/**/*.js'],
                    // the location of the resulting JS file
                    dest: 'src/main/resources/public/<%= pkg.name %>.js'
                }
            },
            uglify: {
                options: {
                    // the banner is inserted at the top of the output
                    banner: '/*! <%= pkg.name %> <%= grunt.template.today("dd-mm-yyyy") %> */\n'
                },
                dist: {
                    files: {
                        'src/main/resources/public/<%= pkg.name %>.min.js': ['<%= concat.dist.dest %>']
                    }
                }
            },
            bowerInstall: {

                target: {

                    // Point to the files that should be updated when
                    // you run `grunt bower-install`
                    src: [
                        'src/main/resources/public/**/*.html',   // .html support...
                    ]
                }
            },
            copy: {
                main: {
                    files: [

                        // makes all src relative to cwd
                        {
                            expand: true,
                            cwd: 'src/main/web',
                            src: ['**/**.css', '**/**.html'],
                            dest: 'src/main/resources/public/'
                        },
                    ],
                },
            },

        });

        grunt.loadNpmTasks('grunt-contrib-copy');
        grunt.loadNpmTasks('grunt-bower-install');
        grunt.loadNpmTasks('grunt-contrib-uglify');
        grunt.loadNpmTasks('grunt-contrib-watch');
        grunt.loadNpmTasks('grunt-contrib-concat');

        grunt.registerTask('default', ['concat', 'uglify', 'copy', 'bowerInstall']);

    };

}(module));