<template>
    <div style="margin: 20px;">
        <div>
            <Row style="margin-bottom: 25px;">
                <Col span="8">课程信息：
                    <Input v-model="loginName" placeholder="请输入..." style="width:200px"></Input>
                </Col>
                <Col span="8">
                    <Button type="primary" shape="circle" icon="ios-search" @click="search()">搜索</Button>
                </Col>
            </Row>
        </div>
        <div>
            <ul>
                <li>
                    <Button type="primary" icon="plus-round" @click="openNewModal()">新建</Button>
                    <Button type="success" icon="wrench" @click="openModifyModal()">修改</Button>
                    <!--<Button type="error" icon="trash-a" @click="del()">删除</Button>-->
                </li>
                <li>
                    <div style="padding: 10px 0;">
                        <Table border :columns="columns1" :data="data1" :height="400"
                               @on-selection-change="s=>{change(s)}" @on-row-dblclick="s=>{dblclick(s)}"></Table>
                    </div>
                </li>
                <li>
                    <div style="text-align: right;">
                        <Page :total="total" :page-size="pageInfo.pageSize" show-elevator show-total
                              @on-change="e=>{pageSearch(e)}"></Page>
                    </div>
                </li>
            </ul>
        </div>
        <!--添加modal-->
        <Modal :mask-closable="false" :visible.sync="newModal" :loading="loading" v-model="newModal" width="600"
               title="新建" @on-ok="newOk('newCourse')" @on-cancel="cancel()">
            <!--<Form ref="newCourse" :model="newCourse" :rules="ruleNew" :label-width="80">-->
            <Form ref="newCourse" :model="newCourse" :label-width="80">
                <Row>
                    <Col span="12">
                        <Form-item label="课程名称:" prop="name">
                            <Input v-model="newCourse.name" style="width: 200px"/>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item label="年度:" prop="year">
                            <Input v-model="newCourse.year" style="width: 200px"/>
                        </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item label="季节班次:">
                            <!--<Select :label-in-value= "true" v-model="configSelect" style="width:200px" @on-change = "e=>{selectSeason(e)}">-->
                            <Select :label-in-value="true" v-model="selectSeason" style="width:200px"
                                    @on-change="e=>{newCourse.season=e.value}">
                                <Option v-for="item in dataCourseSeason" :value="item.value" :key="item.value">
                                    {{ item.description }}
                                </Option>
                            </Select>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item label="课程类型:">
                            <Select :label-in-value="true" v-model="selectType" style="width:200px"
                                    @on-change="e=>{this.newCourse.type=e.value}">
                                <Option v-for="item in dataCourseType" :value="item.value" :key="item.value">
                                    {{ item.description }}
                                </Option>
                            </Select>
                        </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item label="所在校区:">
                            <!--<Select :label-in-value= "true" v-model="configSelect" style="width:200px" @on-change = "e=>{selectSeason(e)}">-->
                            <Select :label-in-value="true" v-model="selectArea" style="width:200px"
                                    @on-change="e=>{this.newCourse.area=e.value}">
                                <Option v-for="item in dataCourseArea" :value="item.value" :key="item.value">
                                    {{ item.description }}
                                </Option>
                            </Select>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item label="学校类型:">
                            <Select :label-in-value="true" v-model="selectSchoolType" style="width:200px"
                                    @on-change="e=>{this.newCourse.schoolType=e.value}">
                                <Option v-for="item in dataCourseSchoolType" :value="item.value" :key="item.value">
                                    {{ item.description }}
                                </Option>
                            </Select>
                        </Form-item>
                    </Col>
                </Row>

                <Row>
                    <Col span="12">
                        <Form-item label="课程进度:">
                            <!--<Select :label-in-value= "true" v-model="configSelect" style="width:200px" @on-change = "e=>{selectSeason(e)}">-->
                            <Select :label-in-value="true" v-model="selectProgress" style="width:200px"
                                    @on-change="e=>{newCourse.progress=e.value}">
                                <Option v-for="item in dataCourseProgress" :value="item.value" :key="item.value">
                                    {{ item.description }}
                                </Option>
                            </Select>
                        </Form-item>
                    </Col>
                    <Col span="12">

                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item label="上课时间:" prop="courseTime">
                            <Input v-model="newCourse.courseTime" style="width: 200px"/>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item label="每节课时间:" prop="perTime">
                            <Input v-model="newCourse.perTime" style="width: 200px"/>
                        </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item label="目标年级:" prop="grade">
                            <Input v-model="newCourse.grade" type="text" style="width: 200px"/>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item label="课程次数:" prop="times">
                            <Input v-model="newCourse.times" type="text" style="width: 200px"/>
                        </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item label="开课日期:" prop="startDate">
                            <Input v-model="newCourse.startDate" type="date" style="width: 200px"/>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item label="结课日期:" prop="endDate">
                            <Input v-model="newCourse.endDate" type="date" style="width: 200px"/>
                        </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item label="单课费用:" prop="startDate">
                            <Input v-model="newCourse.perPrice" type="text" style="width: 200px"/>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item label="材料费:" prop="endDate">
                            <Input v-model="newCourse.materialPay" type="text" style="width: 200px"/>
                        </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item label="课程描述:" prop="description">
                            <Input v-model="newCourse.description" type="textarea" style="width: 450px"/>
                        </Form-item>
                    </Col>
                </Row>

            </Form>
        </Modal>
        <!--修改modal-->
        <Modal :mask-closable="false" :visible.sync="modifyModal" :loading="loading" v-model="modifyModal" width="600"
               title="修改" @on-ok="modifyOk('userModify')" @on-cancel="cancel()">
            <Form ref="userModify" :model="userModify" :rules="ruleModify" :label-width="80">
                <Row>
                    <Col span="12">
                        <Form-item label="登录名:" prop="loginName">
                            <Input v-model="userModify.loginName" style="width: 204px"/>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item label="用户名:" prop="name">
                            <Input v-model="userModify.name" style="width: 204px"/>
                        </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item label="密码:" prop="password">
                            <Input v-model="userModify.password" type="password" style="width: 204px"/>
                        </Form-item>
                    </Col>
                </Row>
                <!--<Row>-->
                <!--<Col span="12">-->
                <!--<Form-item label="邮箱:" prop="email">-->
                <!--<Input v-model="userModify.email" style="width: 204px"/>-->
                <!--</Form-item>-->
                <!--</Col>-->
                <!--</Row>-->
            </Form>
        </Modal>
        <!--详情modal-->
        <Modal v-model="detailModal" width="1000" title="详情" @on-ok="roleOk()" @on-cancel="cancel()">
            <div>
                <Table border :columns="columns2" :data="data2" :height="260"
                       @on-selection-change="s=>{change2(s)}"></Table>
            </div>
        </Modal>
    </div>
</template>
<script>
    export default {
        data: function () {
            return {
                /*用于查找的登录名*/
                loginName: null,
                /*选择的数量*/
                count: null,
                /*选中的组数据*/
                groupId: null,
                /*新建modal的显示参数*/
                newModal: false,
                /*修改modal的显示参数*/
                modifyModal: false,
                /*详情页modal的显示参数*/
                detailModal: false,
                /*分页total属性绑定值*/
                total: 0,
                /*loading*/
                loading: true,
                /*pageInfo实体*/
                pageInfo: {
                    page: 0,
                    pageSize: 10
                },
                /*course实体*/
                course: {
                    id: null,
                    code: null,
                    year: null,
                    area: null,
                    name: null,
                    type: null,
                    progress: null,
                    courseTime: null,
                    season: null,
                    perTime: null,
                    grade: null,
                    times: null,
                    schoolType: null,
                    perPrice: null,
                    materialPay: null,
                    otherPay: null,
                    startDate: null,
                    endDate: null,
                    description: null,
                    status: null,
                    remark: null
                },
                /*course实体*/
                newCourse: {
                    id: null,
                    code: null,
                    year: null,
                    area: null,
                    name: null,
                    type: null,
                    progress: null,
                    season: null,
                    courseTime: null,
                    perTime: null,
                    grade: null,
                    times: null,
                    schoolType: null,
                    perPrice: null,
                    materialPay: null,
                    otherPay: null,
                    startDate: null,
                    endDate: null,
                    description: null,
                    status: null,
                    remark: null
                },
                /*course实体*/
                courseSeason: {
                    id: null,
                    type: null,
                    value: null,
                    description: null,
                    remark: null
                },
                /*用于添加的user实体*/
                userNew: {
                    id: null,
                    name: null,
                    loginName: null,
                    password: null,
                    passwordAgain: null,
                    email: null,
                    tel: null
                },
                /*用于修改的user实体*/
                userModify: {
                    id: null,
                    name: null,
                    loginName: null,
                    password: null,
                    email: null
                },
                /*新建验证*/
                ruleNew: {
                    name: [
                        {type: 'string', required: true, message: '输入用户名', trigger: 'blur'}
                    ],
                    loginName: [
                        {type: 'string', required: true, message: '输入登录名', trigger: 'blur'}
                    ],
                    password: [
                        {type: 'string', required: true, message: '输入密码', trigger: 'blur'}
                    ],
                    passwordAgain: [
                        {type: 'string', required: true, message: '输入再次密码', trigger: 'blur'}
                    ],
                    email: [
                        {required: true, message: '输入邮箱', trigger: 'blur'},
                        {type: 'email', message: '输入正确的邮箱格式', trigger: 'blur'}
                    ],
                    tel: [
                        {type: 'string', required: true, message: '请输入手机号', trigger: 'blur'}
                    ]
                },
                /*修改验证*/
                ruleModify: {
                    name: [
                        {type: 'string', required: true, message: '输入用户名', trigger: 'blur'}
                    ],
                    loginName: [
                        {type: 'string', required: true, message: '输入登录名', trigger: 'blur'}
                    ],
                    password: [
                        {type: 'string', required: true, message: '输入密码', trigger: 'blur'}
                    ],
                    email: [
                        {required: true, message: '输入邮箱', trigger: 'blur'},
                        {type: 'email', message: '输入正确的邮箱格式', trigger: 'blur'}
                    ]
                },
                /*表显示字段*/
                columns1: [
                    {
                        type: 'selection',
                        width: 60,
                        align: 'center'
                    },
                    {
                        title: '课程编码',
                        key: 'code'
                    },
                    {
                        title: '课程名称',
                        key: 'name'
                    },
                    {
                        title: '校区',
                        key: 'area'
                    },
                    {
                        title: '年度',
                        key: 'year'
                    },
                    {
                        title: '进度',
                        key: 'progress'
                    },

                    {
                        title: '操作',
                        align: 'center',
                        key: 'action',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'info',
                                    },
                                    on: {
                                        click: () => {
                                            this.details(params.row);
                                        }
                                    }
                                }, '详情')
                            ]);
                        }
                    },
                ],
                /*表数据*/
                data1: [],
                /*表显示字段*/
                columns2: [
                    {
                        title: '课程名称',
                        width: 100,
                        key: 'name'
                    },
                    {
                        title: '课程类型',
                        width: 100,
                        key: 'type'
                    },
                    {
                        title: '课程进度',
                        width: 100,
                        key: 'progress'
                    },
                    {
                        title: '课程季节',
                        width: 100,
                        key: 'season'
                    },
                    {
                        title: '目标年级',
                        width: 100,
                        key: 'grade'
                    },
                    {
                        title: '上课时间',
                        width: 100,
                        key: 'courseTime'
                    },
                    {
                        title: '单课时间',
                        width: 100,
                        key: 'perTime'
                    },
                    {
                        title: '单课费用',
                        width: 100,
                        key: 'perPrice'
                    },
                    {
                        title: '材料费',
                        width: 80,
                        key: 'materialPay'
                    },
                    {
                        title: '开始日期',
                        width: 100,
                        key: 'startDate'
                    },
                    {
                        title: '结束日期',
                        width: 100,
                        key: 'endDate'
                    },
                    {
                        title: '其他费用',
                        width: 100,
                        key: 'otherPay'
                    }
                ],
                dataCourseConfigTemp: [],
                dataCourseSeason: [],
                dataCourseArea: [],
                dataCourseType: [],
                dataCourseSchoolType: [],
                dataCourseProgress: [],
                /*表数据*/
                data2: [],
                /*data2的临时存储*/
                data2Temp: [],
                configSelect: '',
                selectSeason: '',
                selectArea: '',
                selectSchoolType: '',
                selectType: '',
                selectProgress: '',
                /*用户与角色关系列表*/
                relationList: null
            }
        },
        mounted() {
            /*页面初始化调用方法*/
            this.getTable({
                "pageInfo": this.pageInfo,
                "loginName": this.loginName
            });
            this.getCourseSeasonConfig();
            this.getCourseTypeConfig();
            this.getCourseAreaConfig();
            this.getCourseSchoolTypeConfig();
            this.getCourseProgressConfig();
            this.axios({
                method: 'get',
                // url: '/roles/all'
                url: '/test1'
            }).then(function (response) {
                this.data2Temp = response.data;
            }.bind(this)).catch(function (error) {
                alert(error);
            });
        },
        methods: {
            /*pageInfo实体初始化*/
            initPageInfo() {
                this.pageInfo.page = 0;
                this.pageInfo.pageSize = 10;
            },
            /*user实体初始化*/
            initUser() {
                this.user.id = null;
                this.user.name = null;
                this.user.loginName = null;
                this.user.password = null;
                this.user.email = null;
            },
            /*userNew实体初始化*/
            initNewCourse() {
                this.newCourse.id = null;
                this.newCourse.code = null;
                this.newCourse.year = null;
                this.newCourse.area = null;
                this.newCourse.name = null;
                this.newCourse.type = null;
                this.newCourse.progress = null;
                this.newCourse.season = null;
                this.newCourse.perTime = null;
                this.newCourse.courseTime = null;
                this.newCourse.grade = null;
                this.newCourse.times = null;
                this.newCourse.perPrice = null;
                this.newCourse.materialPay = null;
                this.newCourse.otherPay = null;
                this.newCourse.startDate = null;
                this.newCourse.endDate = null;
                this.newCourse.description = null;
                this.newCourse.remark = null;
            },
            initSelect() {
                this.selectSeason = '';
                this.selectArea = '';
                this.selectSchoolType = '';
                this.selectType = '';
                this.selectProgress = '';
            },
            initUserNew() {
                this.userNew.id = null;
                this.userNew.name = null;
                this.userNew.loginName = null;
                this.userNew.password = null;
                this.userNew.passwordAgain = null;
                this.userNew.email = null;
                this.userNew.tel = null;
            },
            /*userModify实体初始化*/
            initUserModify() {
                this.userModify.id = null;
                this.userModify.name = null;
                this.userModify.loginName = null;
                this.userModify.password = null;
                this.userModify.email = null;
            },
            /*userNew设置*/
            userSet(e) {
                this.user.id = e.id;
                this.user.name = e.name;
                this.user.loginName = e.loginName;
                this.user.password = e.password;
                this.user.email = e.email;
                this.user.passwordAgain = e.passwordAgain;
                this.user.tel = e.tel;
            },
            /*userNew设置*/
            userNewSet(e) {
                this.userNew.id = e.id;
                this.userNew.name = e.name;
                this.userNew.loginName = e.loginName;
                this.userNew.password = e.password;
                this.userNew.passwordAgain = e.password;
                this.userNew.email = e.email;
            },
            /*userModify设置*/
            userModifySet(e) {
                this.userModify.id = e.id;
                this.userModify.name = e.name;
                this.userModify.loginName = e.loginName;
                this.userModify.password = e.password;
                this.userModify.email = e.email;
            },
            /*得到表数据*/
            getTable(e) {
                this.axios({
                    method: 'get',
                    // url: '/users',
                    url: '/courseManager/course',
                    params: {
                        // 'page':e.pageInfo.page,
                        // 'pageSize':e.pageInfo.pageSize,
                        // 'loginName':e.loginName
                    }
                }).then(function (response) {
                    this.data1 = response.data;
                    // this.total=response.data.totalCount;
                }.bind(this)).catch(function (error) {
                    alert(error);
                });
            },
            // 得到配置数据
            getConfig(e) {
                this.dataCourseConfigTemp = '';
                this.axios({
                    method: 'get',
                    url: '/course/config/' + e,
                    params: {}
                }).then(function (response) {
                    this.dataCourseConfigTemp = response.data;
                    // this.total=response.data.totalCount;
                }.bind(this)).catch(function (error) {
                    alert(error);
                });
            },
            getCourseTypeConfig() {
                this.dataCourseConfigTemp = '';
                this.axios({
                    method: 'get',
                    url: '/course/config/COURSE_TYPE',
                    params: {}
                }).then(function (response) {
                    this.dataCourseType = response.data;
                    // this.total=response.data.totalCount;
                }.bind(this)).catch(function (error) {
                    alert(error);
                });
            },
            getCourseSeasonConfig() {
                this.dataCourseConfigTemp = '';
                this.axios({
                    method: 'get',
                    url: '/course/config/COURSE_SEASON',
                    params: {}
                }).then(function (response) {
                    this.dataCourseSeason = response.data;
                    // this.total=response.data.totalCount;
                }.bind(this)).catch(function (error) {
                    alert(error);
                });
            },
            getCourseAreaConfig() {
                this.dataCourseConfigTemp = '';
                this.axios({
                    method: 'get',
                    url: '/course/config/COURSE_AREA',
                    params: {}
                }).then(function (response) {
                    this.dataCourseArea = response.data;
                    // this.total=response.data.totalCount;
                }.bind(this)).catch(function (error) {
                    alert(error);
                });
            },
            getCourseSchoolTypeConfig() {
                this.dataCourseConfigTemp = '';
                this.axios({
                    method: 'get',
                    url: '/course/config/SCHOOL_TYPE',
                    params: {}
                }).then(function (response) {
                    this.dataCourseSchoolType = response.data;
                    // this.total=response.data.totalCount;
                }.bind(this)).catch(function (error) {
                    alert(error);
                });
            },
            getCourseProgressConfig() {
                this.dataCourseConfigTemp = '';
                this.axios({
                    method: 'get',
                    url: '/course/config/COURSE_PROGRESS',
                    params: {}
                }).then(function (response) {
                    this.dataCourseProgress = response.data;
                    // this.total=response.data.totalCount;
                }.bind(this)).catch(function (error) {
                    alert(error);
                });
            },
            /*搜索按钮点击事件*/
            search() {
                this.initPageInfo();
                this.getTable({
                    "pageInfo": this.pageInfo,
                    "loginName": this.loginName
                });
            },
            /*分页点击事件*/
            pageSearch(e) {
                this.pageInfo.page = e - 1;
                this.getTable({
                    "pageInfo": this.pageInfo,
                    "loginName": this.loginName
                });
            },
            /*新建点击触发事件*/
            openNewModal() {
                this.newModal = true;
                // this.initUserNew();
                this.initNewCourse();
                // this.data1.sort();
                this.count = 0;
                this.groupId = null;
            },
            /*新建modal的newOk点击事件*/
            newOk(newCourse) {
                this.$refs[newCourse].validate((valid) => {
                    if (valid) {
                        // this.initUser();
                        // this.userSet(this.userNew);
                        console.log(this.newCourse);
                        this.axios({
                            method: 'put',
                            url: '/courseManager/course',
                            data: this.newCourse
                        }).then(function (response) {
                            // this.initUserNew();
                            this.initNewCourse();
                            this.getTable({
                                "pageInfo": this.pageInfo,
                                "loginName": this.loginName
                            });
                            this.$Message.info('新建成功');
                        }.bind(this)).catch(function (error) {
                            alert(error);
                        });
                        this.newModal = false;
                    } else {
                        this.$Message.error('表单验证失败!');
                        setTimeout(() => {
                            this.loading = false;
                            this.$nextTick(() => {
                                this.loading = true;
                            });
                        }, 1000);
                    }
                });
            },
            /*点击修改时判断是否只选择一个*/
            openModifyModal() {
                if (this.count > 1 || this.count < 1) {
                    this.modifyModal = false;
                    this.$Message.warning('请至少选择一项(且只能选择一项)');
                } else {
                    this.modifyModal = true;
                }
            },
            /*修改modal的modifyOk点击事件*/
            modifyOk(userModify) {
                this.$refs[userModify].validate((valid) => {
                    if (valid) {
                        this.initUser();
                        this.userSet(this.userModify);
                        this.axios({
                            method: 'put',
                            url: '/users/' + this.user.id,
                            data: this.user
                        }).then(function (response) {
                            this.initUserNew();
                            this.getTable({
                                "pageInfo": this.pageInfo,
                                "loginName": this.loginName
                            });
                            this.$Message.info('修改成功');
                        }.bind(this)).catch(function (error) {
                            alert(error);
                        });
                        this.modifyModal = false;
                    } else {
                        this.$Message.error('表单验证失败!');
                        setTimeout(() => {
                            this.loading = false;
                            this.$nextTick(() => {
                                this.loading = true;
                            });
                        }, 1000);
                    }
                })
            },
            /*modal的cancel点击事件*/
            cancel() {
                this.$Message.info('点击了取消');
            },
            /*table选择后触发事件*/
            change(e) {
                if (e.length == 1) {
                    this.userModifySet(e['0']);
                }
                this.setGroupId(e);
            },
            /*通过选中的行设置groupId的值*/
            setGroupId(e) {
                this.groupId = [];
                this.count = e.length;
                for (var i = 0; i <= e.length - 1; i++) {
                    this.groupId.push(e[i].id);
                }
            },
            /*删除table中选中的数据*/
            del() {
                // if (this.groupId != null && this.groupId != "") {
                //     this.axios({
                //         method: 'delete',
                //         url: '/users',
                //         data: this.groupId
                //     }).then(function (response) {
                //         this.getTable({
                //             "pageInfo": this.pageInfo,
                //             "loginName": this.loginName
                //         });
                //         this.groupId = null;
                //         this.count = 0;
                //         this.$Message.info('删除成功');
                //     }.bind(this)).catch(function (error) {
                //         alert(error);
                //     });
                // }
            },
            /*表格中双击事件*/
            dblclick(e) {
                this.userModifySet(e);
                this.modifyModatrue;
                this.data1.sort();
            },
            /*流程配置*/
            relationSet(e) {
                // this.roleModal = true;
                // this.data2 = [];
                // this.axios({
                //     method: 'get',
                //     url: '/relations/' + e.id
                // }).then(function (response) {
                //     var roleList = [];
                //     for (var i in response.data) {
                //         roleList.push(response.data[i].roleId);
                //     }
                //     for (var i in this.data2Temp) {
                //         if (roleList.indexOf(this.data2Temp[i].id) == -1) {
                //             this.data2.push({
                //                 "id": this.data2Temp[i].id,
                //                 "name": this.data2Temp[i].name,
                //                 "describe": this.data2Temp[i].describe,
                //                 "userId": e.id,
                //                 "_checked": false
                //             });
                //         } else {
                //             this.data2.push({
                //                 "id": this.data2Temp[i].id,
                //                 "name": this.data2Temp[i].name,
                //                 "describe": this.data2Temp[i].describe,
                //                 "userId": e.id,
                //                 "_checked": true
                //             });
                //         }
                //     }
                // }.bind(this)).catch(function (error) {
                //     alert(error);
                // });
            },
            /*点击详情*/
            details(e) {
                this.detailModal = true;
                this.data2 = [e];
            },
            /*详情modal确认按钮点击事件*/
            roleOk() {
                // if (this.relationList != null) {
                //     this.axios({
                //         method: 'post',
                //         url: '/relations',
                //         data: this.relationList
                //     }).then(function (response) {
                //         this.$Message.info('配置成功');
                //     }.bind(this)).catch(function (error) {
                //         alert(error);
                //     });
                //     this.relationList = null;
                // }
            },
            /*详情modal中表选择改变事件*/
            change2(e) {
                // this.relationList = [];
                // if (e.length == 0) {
                //     this.relationList.push({
                //         "userId": this.data2[0].userId
                //     });
                // }
                // for (var i in e) {
                //     this.relationList.push({
                //         "userId": e[i].userId,
                //         "roleId": e[i].id
                //     });
                // }
            },
            selectSeason1(e) {
                this.newCourse.season = e.value;
            }
        }
    }
</script>